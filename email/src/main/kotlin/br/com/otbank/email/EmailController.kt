package br.com.otbank.email

import br.com.otbank.email.Email.Companion.toDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@Controller("/api/v1")
class EmailController(@Inject private val repository: EmailRepository) {
    val logger = LoggerFactory.getLogger(this::class.java)

    @Get("/emails")
    fun getAllEmails():HttpResponse<List<EmailDTO>>{
        val emails = repository.findAll()
        val emailsDTO = emails.map { it -> it.toDTO() }
        return HttpResponse.ok(emailsDTO)
    }

    @Get("/email/{id}")
    fun getEmailById(@PathVariable id:Long):HttpResponse<Any>{
        val value = repository.findById(id)
        if(value.isEmpty){
            return HttpResponse.notFound()
        }
        return HttpResponse.ok(value.get().toDTO())
    }
}