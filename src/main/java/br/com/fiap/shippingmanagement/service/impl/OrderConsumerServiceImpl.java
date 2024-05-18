package br.com.fiap.shippingmanagement.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.fiap.shippingmanagement.model.dto.order.GetOrderHistoryResponseDto;
import br.com.fiap.shippingmanagement.service.OrderConsumerService;

@Service
public class OrderConsumerServiceImpl implements OrderConsumerService {

    @KafkaListener(topics = "ECOMMERCE_ORDER_STATUS_CHANGE", groupId = "ShippimentStatus")
    @Override
    public void consume(GetOrderHistoryResponseDto orderHistoryResponseDto) {
        System.out.println("Order received: " + orderHistoryResponseDto.toString());
    }

}
