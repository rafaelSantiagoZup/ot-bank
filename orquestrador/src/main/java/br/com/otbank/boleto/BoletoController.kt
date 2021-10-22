package br.com.otbank.boleto

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import javax.validation.Valid

@Controller("/api/v1/payment/boleto")
@Validated
class BoletoController(@Inject val boletoClient: BoletoClient) {
    @Post
    fun addBoleto(@Body @Valid boletoRequest: BoletoRequest):HttpResponse<Any>{
        val res = boletoClient.addPayment(boletoRequest);
        return res
    }

}