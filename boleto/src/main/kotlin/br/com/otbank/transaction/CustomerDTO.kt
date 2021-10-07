package br.com.otbank.transaction

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Introspected
data class CustomerDTO(
    @field:NotNull
    val clientId:String?,
    @field:Positive
    val balance:BigDecimal
)
