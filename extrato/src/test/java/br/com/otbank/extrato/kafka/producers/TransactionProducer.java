package br.com.otbank.extrato.kafka.producers;

import br.com.otbank.extrato.kafka.consumers.TransactionConsumes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionProducer {

    @Autowired
    private KafkaTemplate<String, TransactionConsumes> kafkaTemplate;

    public void send(String topic, TransactionConsumes payload) {
        kafkaTemplate.send(topic, payload);
    }
}
