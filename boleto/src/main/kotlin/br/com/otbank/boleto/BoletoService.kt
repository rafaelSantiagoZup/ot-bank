package br.com.otbank.boleto

import br.com.otbank.boleto.Boleto.Companion.toBoletoRequest
import br.com.otbank.boleto.BoletoRequest.Companion.toModel
import br.com.otbank.notify.NotifyExtranet
import br.com.otbank.notify.NotifyKafka
import br.com.otbank.notify.NotifyServices
import br.com.otbank.transaction.TransactionDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.scheduling.annotation.Scheduled
import org.slf4j.LoggerFactory
import java.time.LocalDate

class BoletoService(val repository: BoletoRepository,
                    private val accountClient:AccountClient,
                    private val extranetClient:ExtranetClient,
                    private val kafkaBoletoProducer: KafkaBoletoProducer) {

    val logger = LoggerFactory.getLogger(this::class.java)

    fun addBoleto(boletoRequest: BoletoRequest):HttpResponse<Any>{
        if(!compareDates(boletoRequest.expires!!)){
            logger.info("Boleto scheduled for ${boletoRequest.expires}")
            return scheduleBoleto(boletoRequest)
        }
        return payBoleto(boletoRequest)
    }

    private fun payBoleto(boletoRequest: BoletoRequest): HttpResponse<Any> {
        try{
            logger.info("Sending payment request to Bank")
            val transaction = accountClient.paymentBoleto(boletoRequest)
            payAndSave(boletoRequest)
            notifyServices(boletoRequest,transaction.body())
            return HttpResponse.ok()
        }catch (e:HttpClientResponseException){
            return HttpResponse.status(e.status,e.message)
        }
    }

    fun payAndSave(boletoRequest: BoletoRequest){
        boletoRequest.paid = true
        repository.save(boletoRequest.toModel())
    }

    @Scheduled(fixedDelay = "\${request.schedule}")
    fun scheduledTasks(){
        val boletos = repository.findAllByPaidAndExpires(false, LocalDate.now())
        boletos.forEach{ boleto->
            val boletoRequest = boleto.toBoletoRequest()
            payBoleto(boletoRequest)
        }
    }

    fun compareDates(date:LocalDate):Boolean{
        return date.toEpochDay() == LocalDate.now().toEpochDay()
    }

    fun notifyServices(boletoRequest: BoletoRequest,transaction:TransactionDTO) {
       var notified:MutableList<NotifyServices> = mutableListOf(NotifyExtranet(),NotifyKafka())
        notified.forEach { it->it.notifica(extranetClient,kafkaBoletoProducer,boletoRequest,transaction) }
    }

    fun scheduleBoleto(boletoRequest: BoletoRequest):HttpResponse<Any>{
        try{
            val balance = accountClient.clientBalance(boletoRequest.costumerId!!)
            logger.info(balance.body.toString())
            var boleto = boletoRequest.toModel()
            repository.save(boleto)
            logger.info("Payment ${boleto.id} schedulled with success")
            return HttpResponse.ok<Any?>().body("Boleto Agendado com sucesso")
        }catch (e:HttpClientResponseException){
            return HttpResponse.status<Any?>(e.status).body(e.status)
        }
    }
}