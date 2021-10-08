package br.com.otbank.email

import br.com.otbank.transaction.TransactionDTO
import br.com.otbank.transaction.TransactionDTO.Companion.toEmailDTO
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Inject
import org.slf4j.LoggerFactory
import java.util.concurrent.CountDownLatch
import javax.validation.Valid


@KafkaListener(offsetReset = OffsetReset.EARLIEST)
open class EmailListener(@Inject private val emailService: EmailService) {

    val logger = LoggerFactory.getLogger(this::class.java)

    val latch = CountDownLatch(1)

    @Topic("transacoes")
    open fun sendEmail(@Valid transactionDTO: TransactionDTO){
        val success = emailService.sendEmail(transactionDTO.toEmailDTO())
        if(success){
            logger.info("Email delivered with success")
        }else{
            logger.info("Email not sent")
        }
        latch.countDown();
    }
}