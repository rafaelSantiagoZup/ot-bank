package br.com.otbank.transaction

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class TransactionDTO(
    @field:NotBlank
    val id:String?,
    @field:NotNull
    val value:BigDecimal?,
    @field:NotNull
    @field:Enumerated(EnumType.STRING)
    val type: TransactionType?,
    @field:NotNull
    val occurredIn:LocalDateTime?,
    @field:NotNull
    val customer: Customer?
){

}
