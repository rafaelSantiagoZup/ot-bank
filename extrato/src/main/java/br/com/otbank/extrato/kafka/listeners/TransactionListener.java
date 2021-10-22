package br.com.otbank.extrato.kafka.listeners;

import br.com.otbank.extrato.models.Transaction;
import br.com.otbank.extrato.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    @Autowired
    private TransactionRepository transactionRepository;


    private final Logger logger = LoggerFactory.getLogger(TransactionListener.class);

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }


    @KafkaListener(topics = "transacoes")
    public void consumeTopic(Transaction transaction) {
        logger.info(transaction.toString());
        transactionRepository.saveAndFlush(transaction);
        logger.info("Transaction Inserted in the Database");
    }
}