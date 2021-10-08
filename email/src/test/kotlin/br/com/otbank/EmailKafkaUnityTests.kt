package br.com.otbank;

import br.com.otbank.email.EmailRepository
import br.com.otbank.transaction.Account
import br.com.otbank.transaction.Customer
import br.com.otbank.transaction.TransactionDTO
import br.com.otbank.transaction.TransactionType
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
public class EmailKafkaUnityTests {
    @Inject
    lateinit var repository: EmailRepository
    lateinit var transactionRequest: TransactionDTO

    @BeforeEach
    fun setup(){
        val account = Account("2918-1","812757", BigDecimal("1500"))
        val customer = Customer("Rafael Santiago","rafael.santiago@zup.com.br",account)

        transactionRequest = TransactionDTO(
                UUID.randomUUID().toString(),
                BigDecimal("600.00"),
                TransactionType.CREDIT,
                LocalDateTime.now(),
                customer
        )
    }

    @AfterEach
    fun tearDown(){
        repository.deleteAll()
    }
    @Test
    fun `should return right account type`(){
        assertTrue(transactionRequest.type != TransactionType.DEBIT)
        assertTrue(transactionRequest.type == TransactionType.CREDIT)
    }

}
