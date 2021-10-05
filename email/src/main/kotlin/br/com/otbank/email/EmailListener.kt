package br.com.otbank.email

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import org.slf4j.LoggerFactory
import javax.validation.Valid

@KafkaListener
@Validated
class EmailListener(@Inject private val emailService: EmailService) {

    val logger = LoggerFactory.getLogger(this::class.java)

    @Topic("transacoes")
    fun sendEmail(@Valid emailRequest: EmailRequest){
        val success = emailService.sendEmail(emailRequest)
        if(success){
            logger.info("Email delivered with success")
        }else{
            logger.info("Email not sent")
        }
    }
}