package br.com.otbank.extrato.kafka.listeners;

import br.com.otbank.extrato.kafka.consumers.TransactionConsumes;
import br.com.otbank.extrato.models.Transaction;
import br.com.otbank.extrato.repository.AccountRepository;
import br.com.otbank.extrato.repository.CustomerRepository;
import br.com.otbank.extrato.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    private final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    @KafkaListener(id = "transacoes", topics = "transacoes")
    public void consumeTopic(TransactionConsumes transactionConsumes) {

        Transaction transaction = transactionConsumes.toModel(accountRepository, customerRepository);
        transactionRepository.save(transaction);

        logger.info("Transaction Inserted in the Database");
    }
}