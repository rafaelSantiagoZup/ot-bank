package br.com.otbank.boleto

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import javax.validation.Valid

@Validated
@Controller("/api/v1")
class BoletoController(
        @Inject private val service: BoletoService
    ) {

    @Post("/payment")
    fun addBoleto(@Valid @Body boletoRequest: BoletoRequest):HttpResponse<Any>{
        try {
            return service.addBoleto(boletoRequest)
        }catch (e:Exception){
            return HttpResponse.serverError()
        }
    }
}