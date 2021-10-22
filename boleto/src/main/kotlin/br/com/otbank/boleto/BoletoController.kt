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
        @Inject
        val repository: BoletoRepository,
        @Inject
        private val accountClient:AccountClient,
        @Inject
        private val extranetClient:ExtranetClient,
        @Inject
        private val kafkaBoletoProducer: KafkaBoletoProducer
    ) {

    @Post("/payment")
    fun addBoleto(@Valid @Body boletoRequest: BoletoRequest):HttpResponse<Any>{
        try {
            return BoletoService(repository,
                accountClient,
                extranetClient,
                kafkaBoletoProducer)
                .addBoleto(boletoRequest)
        }catch (e:Exception){
            return HttpResponse.serverError()
        }
    }
}