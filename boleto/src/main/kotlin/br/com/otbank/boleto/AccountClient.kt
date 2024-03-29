package br.com.otbank.boleto

import br.com.otbank.transaction.CustomerDTO
import br.com.otbank.transaction.TransactionDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import javax.validation.Valid

@Client("orquestrador")
interface AccountClient {
    @Post("/api/v1/bank")
    fun paymentBoleto(@Body @Valid boleto:BoletoRequest): HttpResponse<TransactionDTO>
    @Get("/api/v1/bank/balance/{id}")
    fun clientBalance(@PathVariable id:String): HttpResponse<CustomerDTO>
}