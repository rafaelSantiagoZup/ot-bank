package br.com.otbank.notify

import br.com.otbank.boleto.BoletoRequest
import br.com.otbank.boleto.ExtranetClient
import br.com.otbank.boleto.KafkaBoletoProducer
import br.com.otbank.transaction.TransactionDTO
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.slf4j.LoggerFactory

class NotifyExtranet:NotifyServices {
    val logger = LoggerFactory.getLogger(this::class.java)
    override fun notifica(
        extranet: ExtranetClient,
        kafka: KafkaBoletoProducer,
        boletoRequest: BoletoRequest,
        transaction: TransactionDTO
    ) {
        try {
            extranet.paymentBoleto(boletoRequest)
            logger.info("Extranet notified")
        } catch (e:HttpClientResponseException){
            logger.info("Error notifing extranet: ${e.message}")
        }
    }

}