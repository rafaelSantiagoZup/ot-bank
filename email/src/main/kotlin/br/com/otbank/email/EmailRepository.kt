package br.com.otbank.email

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface EmailRepository:JpaRepository<Email,Long> {
    fun existsByRecipientEmailAndMessage(recipientEmail:String,message:String):Boolean
}