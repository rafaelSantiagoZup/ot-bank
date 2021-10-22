package br.com.otbank.transaction

import io.micronaut.validation.Validated
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Validated
data class TransactionDTO(
    @field:NotBlank
    val clientId:String?,
    @field:NotNull
    val type: TransactionType?,
    @field:NotNull
    val value: BigDecimal?
)