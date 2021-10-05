package br.com.otbank

import io.micronaut.core.annotation.Introspected
import javax.persistence.Column
import javax.validation.constraints.NotBlank

@Introspected
data class EmailRequest(
    @field:NotBlank
    val sender:String?,
    @field:NotBlank
    val recipientEmail:String?,
    @field:NotBlank
    val recipientName:String?,
    @field:NotBlank
    val subject:String?,
    @field:NotBlank
    @Column(length = 2000)
    val message:String?
) {
    companion object{
        fun EmailRequest.toModel():Email{
            return Email(sender,recipientEmail,recipientName,subject,message)
        }
    }
}