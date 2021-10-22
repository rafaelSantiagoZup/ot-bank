package br.com.otbank.notify

import br.com.otbank.boleto.BoletoRequest
import br.com.otbank.boleto.ExtranetClient
import br.com.otbank.boleto.KafkaBoletoProducer
import br.com.otbank.transaction.TransactionDTO
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.slf4j.LoggerFactory

class NotifyKafka:NotifyServices {
    override fun notifica(
        extranet: ExtranetClient,
        kafka: KafkaBoletoProducer,
        boletoRequest: BoletoRequest,
        transaction: TransactionDTO
    ) {
        val logger = LoggerFactory.getLogger(this::class.java)
        try {
            kafka.sendTransactionMessage(transaction)
            logger.info("Kafka notified")
        } catch (e: HttpClientResponseException){
            logger.info("Error notifing Kafka: ${e.message}")
        }
    }
}