package br.com.otbank

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface EmailRepository:JpaRepository<Email,Long> {
}