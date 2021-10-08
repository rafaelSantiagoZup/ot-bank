package br.com.otbank.boleto

import br.com.otbank.boleto.Boleto.Companion.toBoletoRequest
import br.com.otbank.boleto.BoletoRequest.Companion.toModel
import br.com.otbank.transaction.*
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@MicronautTest
class BoletoIntegrationTests {

    @Inject
    lateinit var repository: BoletoRepository
    @Inject
    lateinit var accountClient:AccountClient
    @Inject
    lateinit var extranetClient:ExtranetClient
    @Inject
    lateinit var service: BoletoService
    @Inject
    lateinit var kafkaBoletoProducer: KafkaBoletoProducer

    @Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var transactionRequest:TransactionDTO
    lateinit var boletoRequest: BoletoRequest

    val customerId:String = UUID.randomUUID().toString()
    var timestamp = LocalDateTime.now()

    @BeforeEach
    fun setup(){
        val account = Account("2918-1","812757", BigDecimal("1500"))
        val customer = Customer(customerId,"Rafael Santiago","rafael.santiago@zup.com.br",account)

        transactionRequest = TransactionDTO(
            UUID.randomUUID().toString(),
            BigDecimal("20.00"),
            TransactionType.DEBIT,
            timestamp,
            customer
        )
        boletoRequest = BoletoRequest(customerId, BigDecimal("20.00"), LocalDate.now())

    }

    @AfterEach
    fun tearDown(){
        repository.deleteAll()
    }

    @Test
    fun `should return all expired and not paid`(){
        repository.save(boletoRequest.toModel())

        val fromRepo = repository.findAllByPaidAndExpires(false, LocalDate.now())

        assertEquals(fromRepo.isEmpty(),false)
        assertEquals(fromRepo.size,1)
        assertEquals(fromRepo[0].toBoletoRequest(),boletoRequest)
    }
    @Test
    fun `should return empty list when not expired saved`(){
        var boleto = boletoRequest.toModel()
        boleto.expires = LocalDate.now().plusDays(1)

        repository.save(boleto)

        val fromRepo = repository.findAllByPaidAndExpires(false, LocalDate.now())

        assertTrue(fromRepo.isEmpty())
    }

//    @Test
//    fun `should return ok when all flux is correct`(){
//        println(boletoRequest.toString())
//        val request = HttpRequest.POST("api/v1/payment",boletoRequest)
//
//        Mockito.`when`(accountClient.clientBalance(customerId)).thenReturn(
//            HttpResponse.ok(CustomerDTO(customerId, BigDecimal("200.00")))
//        )
//        Mockito.`when`(accountClient.paymentBoleto(boletoRequest)).thenReturn(
//            HttpResponse.ok<TransactionDTO?>().body(transactionRequest)
//        )
//        Mockito.`when`(extranetClient.paymentBoleto(boletoRequest))
//            .thenReturn(HttpResponse.ok(boletoRequest))
//
//        var response: HttpResponse<BoletoRequest>? = client.toBlocking().exchange(request,BoletoRequest::class.java)
//        assertEquals(response?.status,HttpStatus.OK)
//    }
    @Test
    fun `requestExternalServices should return HttpResponse ok`(){
        Mockito.`when`(accountClient.clientBalance(customerId)).thenReturn(
            HttpResponse.ok(CustomerDTO(customerId, BigDecimal("200.00")))
        )
        Mockito.`when`(accountClient.paymentBoleto(boletoRequest)).thenReturn(
            HttpResponse.ok(transactionRequest)
        )
        Mockito.`when`(extranetClient.paymentBoleto(boletoRequest)).thenReturn(HttpResponse.ok(boletoRequest))

        val responseServer = service.requestExternalServices(boletoRequest)

        assertEquals(responseServer.status,HttpStatus.OK)
    }
//    @Test
//    fun `externalCommunication should return HttpResponse ok`(){
//        Mockito.`when`(accountClient.clientBalance(customerId)).thenReturn(
//            HttpResponse.ok(CustomerDTO(customerId, BigDecimal("200.00")))
//        )
//        Mockito.`when`(accountClient.paymentBoleto(boletoRequest)).thenReturn(
//            HttpResponse.ok(transactionRequest)
//        )
//        Mockito.`when`(extranetClient.paymentBoleto(boletoRequest)).thenReturn(HttpResponse.ok(boletoRequest))
//
//        val responseServer = service.externalCommunication(boletoRequest)
//
//        assertEquals(responseServer.status,HttpStatus.OK)
//    }

    @MockBean(AccountClient::class)
    fun accountMockingBean():AccountClient{
        return Mockito.mock(AccountClient::class.java,Mockito.RETURNS_DEEP_STUBS)
    }
    @MockBean(ExtranetClient::class)
    fun extranetMockingBean():ExtranetClient{
        return Mockito.mock(ExtranetClient::class.java,Mockito.RETURNS_MOCKS)
    }
}