package br.com.otbank.boleto

import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
@Introspected
class Boleto(
    @field:NotNull
    val costumerId:String?,
    @field:Positive
    @field:NotNull
    val value:BigDecimal?,
    @field:FutureOrPresent
    var expires: LocalDate?,
    var paid:Boolean?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    companion object{
        fun Boleto.toBoletoRequest():BoletoRequest{
            return BoletoRequest(costumerId,value,expires)
        }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Boleto) return false

        if (costumerId != other.costumerId) return false
        if (value != other.value) return false
        if (expires != other.expires) return false
        if (paid != other.paid) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = costumerId.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (expires?.hashCode() ?: 0)
        result = 31 * result + (paid?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }


}