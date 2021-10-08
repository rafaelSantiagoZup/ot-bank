package br.com.otbank.transaction

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class Account(
    @field:NotBlank
    val agency:String,
    @field:NotNull
    val accountNumber:String,
    @field:NotNull
    val balance:BigDecimal
)
