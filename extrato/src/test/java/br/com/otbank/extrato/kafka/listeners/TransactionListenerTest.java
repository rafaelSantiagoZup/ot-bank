package br.com.otbank.extrato.kafka.listeners;

import br.com.otbank.extrato.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class TransactionListenerTest {
    @Autowired
    private TransactionRepository repository;

    @Test
    public void itShouldPersistTransactionAfterMessageReceived() {
        repository.findByContaId()
    }
}