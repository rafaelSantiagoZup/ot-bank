package br.com.otbank.transaction

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Introspected
data class Customer (
    @field:NotBlank
    val customerId:String,
    @field:NotBlank
    val name:String,
    @field:NotBlank
    val email:String,
    @field:NotNull
    val account: Account
    )
