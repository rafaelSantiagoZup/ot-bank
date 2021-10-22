package br.com.otbank.transaction

import br.com.otbank.boleto.BoletoRequest
import br.com.otbank.boleto.BoletoRequest.Companion.toTransactionDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import javax.validation.Valid

@Controller("/api/v1/bank")
class ContaDigitalController(@Inject val contaDigitalClient: ContaDigitalClient) {
    @Get("/balance/{id}")
    fun getBalance(@PathVariable id:String):HttpResponse<CustomerDTO>{
        return contaDigitalClient.getBalance(id);
    }
    @Post
    fun paymentBoleto(@Body @Valid boletoRequest: BoletoRequest):HttpResponse<Transaction>{
        return contaDigitalClient.addTransaction(boletoRequest.toTransactionDTO())
    }

}