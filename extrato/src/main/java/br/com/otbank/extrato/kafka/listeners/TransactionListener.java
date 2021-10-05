package br.com.otbank.extrato.kafka.listeners;

import br.com.otbank.extrato.kafka.consumers.TransactionConsumes;
import br.com.otbank.extrato.models.BankTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class TransactionListener {
    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void consumeTopic(TransactionConsumes transactionConsumes) {
        BankTransactional bankTransactional = transactionConsumes.toModel();
        entityManager.persist(bankTransactional);

        logger.info("Transaction Inserted in the Database");
    }
}