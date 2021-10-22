package br.com.otbank.transaction

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface KafkaTransactionProducer {
    @Topic("transacoes")
    fun sendTransactionMessage(transaction: Transaction)

}