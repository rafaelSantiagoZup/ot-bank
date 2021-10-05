package br.com.otbank

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotBlank

@Entity
@Introspected
class Email(
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null
    @field:FutureOrPresent
    val timestamp:LocalDateTime? = LocalDateTime.now()



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Email) return false

        if (sender != other.sender) return false
        if (recipientEmail != other.recipientEmail) return false
        if (recipientName != other.recipientName) return false
        if (message != other.message) return false
        if (id != other.id) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sender?.hashCode() ?: 0
        result = 31 * result + (recipientEmail?.hashCode() ?: 0)
        result = 31 * result + (recipientName?.hashCode() ?: 0)
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (timestamp?.hashCode() ?: 0)
        return result
    }


}