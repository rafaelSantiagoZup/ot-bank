package br.com.otbank.boleto

import br.com.otbank.transaction.TransactionDTO
import br.com.otbank.transaction.TransactionType
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDate
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Introspected
data class BoletoRequest(
    @field:NotNull
    val costumerId: String?,
    @field:Positive
    @field:NotNull
    val value:BigDecimal?,
    @field:FutureOrPresent
    val expires: LocalDate?
){
    companion object{
        fun BoletoRequest.toTransactionDTO():TransactionDTO{
            return TransactionDTO(costumerId,TransactionType.DEBIT,value)
        }
    }
}