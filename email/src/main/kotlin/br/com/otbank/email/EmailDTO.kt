package br.com.otbank.email

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import javax.persistence.Column
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank

@Introspected
data class EmailDTO (
    @field:NotBlank
    val recipientEmail:String?,
    @field:NotBlank
    val recipientName:String?,
    @field:NotBlank
    val subject:String?,
    @field:NotBlank
    @Column(length = 2000)
    val message:String?,
    @field:FutureOrPresent
    val timestamp: LocalDateTime?
    )
