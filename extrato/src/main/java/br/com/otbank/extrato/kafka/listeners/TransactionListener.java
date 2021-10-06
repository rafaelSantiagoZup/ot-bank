package br.com.otbank.extrato.kafka.listeners;

import br.com.otbank.extrato.kafka.consumers.TransactionConsumes;
import br.com.otbank.extrato.models.BankTransactional;
import br.com.otbank.extrato.repository.AccountRepository;
import br.com.otbank.extrato.repository.CustomerRepository;
import br.com.otbank.extrato.repository.TransactionalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionalRepository transactionalRepository;

    private final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void consumeTopic(TransactionConsumes transactionConsumes) {
        BankTransactional bankTransactional = transactionConsumes.toModel(accountRepository, customerRepository);
        transactionalRepository.save(bankTransactional);

        logger.info("Transaction Inserted in the Database");
    }
}