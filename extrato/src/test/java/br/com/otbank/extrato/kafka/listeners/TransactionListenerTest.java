package br.com.otbank.extrato.kafka.listeners;

import br.com.otbank.extrato.kafka.consumers.AccountConsumes;
import br.com.otbank.extrato.kafka.consumers.CustomerConsumes;
import br.com.otbank.extrato.kafka.consumers.TransactionConsumes;
import br.com.otbank.extrato.kafka.producers.TransactionProducer;
import br.com.otbank.extrato.models.TransactionType;
import br.com.otbank.extrato.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class TransactionListenerTest {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionListener listener;

    @Autowired
    private TransactionProducer producer;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void itShouldPersistTransactionAfterMessageReceived() {
        CustomerConsumes customer = new CustomerConsumes("testeCustomer", "Somais Um Silva", "djastra@bol.com");
        AccountConsumes account = new AccountConsumes("testeAccount", "01", "12345", BigDecimal.valueOf(20.00), customer);
        TransactionConsumes payload = new TransactionConsumes("teste", BigDecimal.valueOf(2.00), TransactionType.DEBIT, account);

        producer.send("transacoes", payload);
        await().atMost(5, TimeUnit.SECONDS).until(() -> repository.findAll().size() == 1);

        Assert.isTrue(repository.findAll().size() == 1, "A transação não foi gravada no banco");
    }
}