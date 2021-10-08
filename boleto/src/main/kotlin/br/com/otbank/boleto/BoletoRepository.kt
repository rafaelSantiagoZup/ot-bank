package br.com.otbank.boleto

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.time.LocalDate

@Repository
interface BoletoRepository:JpaRepository<Boleto,Long> {
    fun findAllByPaidAndExpires(paid:Boolean,expires:LocalDate):List<Boleto>
}