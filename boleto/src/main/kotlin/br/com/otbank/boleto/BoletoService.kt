package br.com.otbank.boleto

import br.com.otbank.boleto.Boleto.Companion.toBoletoRequest
import br.com.otbank.boleto.BoletoRequest.Companion.toModel
import br.com.otbank.transaction.TransactionDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.time.LocalDate

@Singleton
class BoletoService(@Inject
                    val repository: BoletoRepository,
                    @Inject
                    private val accountClient:AccountClient,
                    @Inject
                    private val extranetClient:ExtranetClient,
                    @Inject
                    private val kafkaBoletoProducer: KafkaBoletoProducer) {

    fun addBoleto(boletoRequest: BoletoRequest):HttpResponse<Any>{
        val balance = accountClient.clientBalance(boletoRequest.costumerId!!)
        if(balance.status == HttpStatus.NOT_FOUND){
            return HttpResponse.notFound();
        }
        if(balance.body().balance.compareTo(boletoRequest.value) == -1){
            return HttpResponse.unprocessableEntity<Any?>().body("O saldo é menor do que o valor do boleto")
        }
        if(compareDates(boletoRequest.expires!!)){
            return externalCommunication(boletoRequest)
        }
        var boleto = boletoRequest.toModel()
        repository.save(boleto)
        return HttpResponse.ok<Any?>().body("Boleto Agendado com sucesso")
    }

    @Scheduled(fixedDelay = "\${request.schedule}")
    fun scheduledTasks(){
        val boletos = repository.findAllByPaidAndExpires(false, LocalDate.now())
        boletos.forEach{ boleto->
            val boletoRequest = boleto.toBoletoRequest()
            val responseAccount = requestExternalServices(boletoRequest)
            if(responseAccount.status == HttpStatus.OK){
                emitMessageAndSave(responseAccount, boletoRequest)
            }
        }
    }

    fun compareDates(date:LocalDate):Boolean{
        return date == LocalDate.now()
    }

    fun externalCommunication(boletoRequest: BoletoRequest): HttpResponse<Any> {
        try{
            val responseAccount = requestExternalServices(boletoRequest)
            println("status code "+responseAccount.status)
            if(responseAccount.status == HttpStatus.OK){
                emitMessageAndSave(responseAccount, boletoRequest)
                return HttpResponse.ok()
            }
            else{
                println(responseAccount.status())
                return HttpResponse.unprocessableEntity()
            }
        } catch (e:Exception){
            return HttpResponse.serverError()
        }
    }

    private fun emitMessageAndSave(
        responseAccount: HttpResponse<TransactionDTO>,
        boletoRequest: BoletoRequest
    ) {
        kafkaBoletoProducer.sendTransactionMessage(responseAccount.body())
        val boleto = boletoRequest.toModel()
        boleto.paid = true
        repository.save(boleto)
    }

    fun requestExternalServices(boletoRequest: BoletoRequest): HttpResponse<TransactionDTO> {
        extranetClient.paymentBoleto(boletoRequest)
        return accountClient.paymentBoleto(boletoRequest)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BoletoService) return false

        if (repository != other.repository) return false
        if (accountClient != other.accountClient) return false
        if (extranetClient != other.extranetClient) return false
        if (kafkaBoletoProducer != other.kafkaBoletoProducer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = repository.hashCode()
        result = 31 * result + accountClient.hashCode()
        result = 31 * result + extranetClient.hashCode()
        result = 31 * result + kafkaBoletoProducer.hashCode()
        return result
    }


}