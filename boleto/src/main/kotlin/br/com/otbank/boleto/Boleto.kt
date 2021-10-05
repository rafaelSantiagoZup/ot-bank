package br.com.otbank.boleto

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Boleto(
    val Intituicao:String?,
    val valor:BigDecimal?,
    val vencimento:LocalDateTime?,
    val codigoDeBarras:String?,
    val pago:Boolean?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

}