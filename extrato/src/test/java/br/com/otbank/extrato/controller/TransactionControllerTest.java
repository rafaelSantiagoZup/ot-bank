package br.com.otbank.extrato.controller;

import br.com.otbank.extrato.models.Account;
import br.com.otbank.extrato.models.Customer;
import br.com.otbank.extrato.models.Transaction;
import br.com.otbank.extrato.models.TransactionType;
import br.com.otbank.extrato.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void itShouldReturnTheLast20Transactions() throws Exception {
        Customer customer = new Customer("testeCustomer", "Somais Um Silva", "djastra@bol.com");
        Account account = new Account("testeAccount", "01", "12345", BigDecimal.valueOf(20.00), customer);
        Transaction transaction = new Transaction("teste", BigDecimal.valueOf(2.00), TransactionType.DEBIT, account);

        repository.save(transaction);

        mockMvc.perform(get("/testeAccount/transacoes").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}