package br.com.otbank.boleto

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
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
    var paid = false
    companion object{
        fun BoletoRequest.toModel():Boleto{
            return Boleto(costumerId,value,expires,paid)
        }
    }
}