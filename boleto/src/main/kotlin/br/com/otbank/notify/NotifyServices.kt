package br.com.otbank.notify

import br.com.otbank.boleto.BoletoRequest
import br.com.otbank.boleto.ExtranetClient
import br.com.otbank.boleto.KafkaBoletoProducer
import br.com.otbank.transaction.TransactionDTO

interface NotifyServices {
    fun notifica(
        extranet: ExtranetClient,
        kafka: KafkaBoletoProducer,
        boletoRequest: BoletoRequest,
        transaction: TransactionDTO
    )
}