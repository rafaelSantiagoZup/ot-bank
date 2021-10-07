package br.com.otbank.boleto

import br.com.otbank.transaction.TransactionDTO
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface KafkaBoletoProducer {
    @Topic("\${kafka.boleto-topic}")
    fun sendTransactionMessage(transactionDTO: TransactionDTO)
}