package br.com.otbank.boleto

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class BoletoController {
    @Get("/hello")
    fun hello():HttpResponse<String>{
        return HttpResponse.ok("Hello world")
    }
}