package br.com.otbank

import br.com.otbank.EmailRequest.Companion.toModel
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Validated
class EmailService(private val repository: EmailRepository) {
    val logger = LoggerFactory.getLogger(this::class.java)

    fun sendEmail(@Valid email: EmailRequest):Boolean{
        logger.info(
            "from:${email.sender}\n" +
            "to:${email.recipientEmail}\n" +
            "subject:${email.subject} \n" +
            "Prezado(a) ${email.recipientName},\n" +
            "${email.message}"
        )
        try{
            repository.save(email.toModel())
            return true
        }catch (e:Exception){
            return false
        }
    }
}