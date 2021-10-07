package br.com.otbank.boleto

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import javax.validation.Valid

@Client("http://localhost:8085/api/v1/payment/boleto")
interface AccountClient {
    @Post
    fun paymentBoleto(@Body @Valid boleto:BoletoRequest): HttpResponse<Any>
}