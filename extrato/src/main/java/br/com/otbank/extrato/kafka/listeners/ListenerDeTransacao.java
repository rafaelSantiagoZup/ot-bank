package br.com.otbank.extrato.kafka.listeners;

import br.com.otbank.extrato.kafka.consumers.TransacaoConsumer;
import br.com.otbank.extrato.models.TransacaoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class ListenerDeTransacao {

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(ListenerDeTransacao.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void consumirTopico(TransacaoConsumer transacaoConsumer) {
        TransacaoModel transacao = transacaoConsumer.toModel();
        entityManager.persist(transacao);

        logger.info("Transação Inserida no Banco de Dados");
    }
}