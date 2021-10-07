package br.com.otbank.boleto

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import javax.validation.Valid

@Client("\${client.extranet}")
interface ExtranetClient {
    @Post
    fun paymentBoleto(@Body @Valid boleto:BoletoRequest): HttpResponse<Any>
}