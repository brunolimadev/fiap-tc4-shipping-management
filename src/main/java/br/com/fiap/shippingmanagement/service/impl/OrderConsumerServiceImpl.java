package br.com.fiap.shippingmanagement.service.impl;

import br.com.fiap.shippingmanagement.enumarators.StatusEnum;
import br.com.fiap.shippingmanagement.model.dto.ShippingRequestDto;
import br.com.fiap.shippingmanagement.service.OrderConsumerService;
import br.com.fiap.shippingmanagement.service.OrderProducerService;
import br.com.fiap.shippingmanagement.service.ShippingService;

import com.google.gson.Gson;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.fiap.shippingmanagement.model.dto.order.OrderHistoryDto;

import java.io.IOException;

@Service
public class OrderConsumerServiceImpl implements OrderConsumerService {

    @Value(value = "${kafka.topic.order.sent}")
    private String topicOrderSend;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private OrderProducerService orderProducerService;

    @KafkaListener(topics = "ECOMMERCE_ORDER_WAITING_SHIPMENT", groupId = "ShippimentStatus")
    @Override
    public void consume(String message) {
        System.out.println("Order received: " + message);

        var orderHistoryDto = new Gson().fromJson(message, OrderHistoryDto.class);

        var shippingRequestDtoDto = new ShippingRequestDto(
                orderHistoryDto.getClientId(),
                orderHistoryDto.getOrderId());

        var shippingResponseDto = shippingService.saveShipping(shippingRequestDtoDto);

        var shippingId = shippingResponseDto.id();

        System.out.println("Pedido de entrega criado do sucesso, SHIPPING_ID: " + shippingId);

        try {
            shippingService.assignDriverToShipment(shippingId);
        } catch (IOException | InterruptedException | ApiException e) {
            System.out.println("Erro ao atribuir um motorista  ao pedido de entrega! " + e.getMessage());
            throw new RuntimeException(e);
        }

        System.out.println("Motorista foi atribuido com sucesso!");
        orderHistoryDto.setStatus(StatusEnum.SENT.name());

        orderProducerService.sendTopic(
                topicOrderSend,
                orderHistoryDto.getOrderId(),
                new Gson().toJson(orderHistoryDto)
        );
    }
}
