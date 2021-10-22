package br.com.otbank.transaction

import br.com.otbank.transaction.CustomerDTO
import br.com.otbank.transaction.Transaction
import br.com.otbank.transaction.TransactionDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("\${clients.conta-digital}")
interface ContaDigitalClient {
    @Get("/accounts/{id}")
    fun getBalance(@PathVariable id:String):HttpResponse<CustomerDTO>

    @Post("/accounts")
    fun addTransaction(@Body transaction: TransactionDTO):HttpResponse<Transaction>
}