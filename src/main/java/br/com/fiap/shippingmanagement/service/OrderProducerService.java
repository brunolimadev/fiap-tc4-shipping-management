package br.com.fiap.shippingmanagement.service;

public interface OrderProducerService {

    void sendTopic(String topic, String id, String order);

}
