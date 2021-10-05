package br.com.otbank.email

import br.com.otbank.email.EmailRequest.Companion.toModel
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import javax.validation.Valid

@Validated
@Singleton
class EmailService(@Inject private val repository: EmailRepository) {
    val logger = LoggerFactory.getLogger(this::class.java)

    fun sendEmail(@Valid email: EmailRequest):Boolean{
        if(!checkEmailExistence(email)){
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
        else{
            logger.info("This email has already been sent")
            return false
        }

    }

    fun checkEmailExistence(@Valid request: EmailRequest):Boolean{
        return repository.existsByRecipientEmailAndMessage(request.recipientEmail!!,request.message!!)
    }
}