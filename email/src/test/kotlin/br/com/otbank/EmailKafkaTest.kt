package br.com.otbank

import br.com.otbank.email.*
import br.com.otbank.email.EmailRequest.Companion.toModel
import br.com.otbank.transaction.Account
import br.com.otbank.transaction.Customer
import br.com.otbank.transaction.TransactionDTO
import br.com.otbank.transaction.TransactionDTO.Companion.toEmailDTO
import br.com.otbank.transaction.TransactionType
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.awaitility.Awaitility.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MicronautTest
class EmailKafkaTest {
    @Inject
    lateinit var kafkaClient: KafkaEmailClient
    @Inject
    lateinit var repository: EmailRepository
    @Inject
    lateinit var listener: EmailListener
    @Inject
    lateinit var emailService: EmailService
    @Inject
    @field:Client("/")
    lateinit var client:HttpClient

    private lateinit var transactionRequest: TransactionDTO

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
    fun `should save email in database`(){
        kafkaClient.sendEmail(transactionRequest)

        await().atMost(5,TimeUnit.SECONDS).until { repository.findAll().size == 1 }

        val responseFromServer = repository.findAll()


        assertTrue(responseFromServer.size > 0)
        assertEquals(responseFromServer[0].recipientEmail,transactionRequest.customer?.email)
        assertEquals(responseFromServer[0].message,transactionRequest.toEmailDTO().message)
        assertEquals(responseFromServer[0].sender,"contact@otbank.com.br")
        assertEquals(responseFromServer[0].recipientName,transactionRequest.toEmailDTO().recipientName)
        assertEquals(responseFromServer[0].subject,transactionRequest.toEmailDTO().subject)
    }
    @Test
    fun `should not save duplicated values`(){
        val ok = emailService.sendEmail(transactionRequest.toEmailDTO())
        val error = emailService.sendEmail(transactionRequest.toEmailDTO())

        val responseFromServer = repository.findAll()

        assertTrue(ok)
        assertTrue(!error)
        assertEquals(responseFromServer.size, 1)
    }
    @Test
    fun `should return ok when REST get all emails request`(){
        kafkaClient.sendEmail(transactionRequest)

        listener.latch.await(10000,TimeUnit.MILLISECONDS)
        val request = HttpRequest.GET<Any>("api/v1/emails")
        val response:HttpResponse<List<EmailDTO>> = client.toBlocking().exchange(request)
        assertEquals(response.status,HttpStatus.OK)
    }
    @Test
    fun `should return true when the email is already registered`(){
        repository.save(transactionRequest.toEmailDTO().toModel())

        assertTrue(emailService.checkEmailExistence(transactionRequest.toEmailDTO()))
    }
    @Test
    fun `should return ok when search by id REST`(){
        kafkaClient.sendEmail(transactionRequest)

        await().atMost(5,TimeUnit.SECONDS).until { repository.findAll().size == 1 }
        
        val responseFromRepo = repository.findAll()
        val idReq = responseFromRepo[0].id

        val request = HttpRequest.GET<Any>("api/v1/email/${idReq}")
        val response:HttpResponse<EmailDTO> = client.toBlocking().exchange(request)
        assertEquals(response.status,HttpStatus.OK)
    }
}