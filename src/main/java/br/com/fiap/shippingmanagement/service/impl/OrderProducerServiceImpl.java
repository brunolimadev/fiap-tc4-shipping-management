package br.com.fiap.shippingmanagement.service.impl;

import br.com.fiap.shippingmanagement.service.OrderProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducerServiceImpl implements OrderProducerService {

    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTopic(String topic, String id, String order) {
        var record = new ProducerRecord<>(topic, id, order);
        kafkaTemplate.send(record);
    }

}
