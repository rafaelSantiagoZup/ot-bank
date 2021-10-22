package br.com.otbank.boleto

import br.com.otbank.boleto.BoletoRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("\${clients.boleto}")
interface BoletoClient {

    @Post("payment")
    fun addPayment(@Body request: BoletoRequest):HttpResponse<Any>
}