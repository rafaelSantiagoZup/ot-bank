package br.com.otbank.email

import br.com.otbank.transaction.TransactionDTO
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface KafkaEmailClient {
    @Topic("transacoes")
    fun sendEmail(transactionDTO: TransactionDTO)
}