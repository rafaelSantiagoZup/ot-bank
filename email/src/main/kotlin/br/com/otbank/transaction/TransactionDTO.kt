package br.com.otbank.transaction

import br.com.otbank.email.EmailRequest
import io.micronaut.core.annotation.Introspected
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
    companion object{
        fun TransactionDTO.toEmailDTO():EmailRequest{
            var message = ""
            var subject = ""
            val date = formatDateTime(occurredIn!!)
            if(type?.equals(TransactionType.CREDIT) == true){
                message = "Foi creditado na sua conta o valor de ${value} em ${date}"
                subject = "Crédito em conta"
            }
            else{
                message = "Foi debitado da sua conta o valor de ${value} em ${date}"
                subject = "Débito em conta"
            }
            return EmailRequest("contact@otbank.com.br",customer?.email,customer?.name,subject,message)
        }
    }
    fun formatDateTime(dateTime: LocalDateTime):String{
        val formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val formatterTime = DateTimeFormatter.ofPattern("hh:mm")
        return "${dateTime.format(formatterDate)} às ${dateTime.format(formatterTime)}"
    }
}
