package br.com.fiap.shippingmanagement.service.impl;

import br.com.fiap.shippingmanagement.model.Shipping;
import br.com.fiap.shippingmanagement.model.dto.ShippingRequestDto;
import br.com.fiap.shippingmanagement.service.ShippingService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.fiap.shippingmanagement.model.dto.order.GetOrderHistoryResponseDto;
import br.com.fiap.shippingmanagement.service.OrderConsumerService;

import java.io.IOException;


@Service
public class OrderConsumerServiceImpl implements OrderConsumerService {

    @Autowired
    private ShippingService shippingService;

    @KafkaListener(topics = "ECOMMERCE_ORDER_STATUS_CHANGE", groupId = "ShippimentStatus")
    @Override
    public void consume(GetOrderHistoryResponseDto orderHistoryResponseDto) {
        System.out.println("Order received: " + orderHistoryResponseDto.toString());

        var shippingRequestDtoDto = new ShippingRequestDto(
                orderHistoryResponseDto.getClientId(),
                orderHistoryResponseDto.getOrderId()
        );

        var shippingResponseDto =  shippingService.saveShipping(shippingRequestDtoDto);
        var shippingId = shippingResponseDto.id();

        System.out.println("Peido de entrega criado do sucesso, SHIPPING_ID: " + shippingId);

        try {
            shippingService.assignDriverToShipment(shippingId);
        } catch (IOException | InterruptedException | ApiException e) {
            System.out.println("Erro ao atribuir um motorista  ao pedido de entrega! " + e.getMessage());
            // throw new RuntimeException(e);
        }

        System.out.println("Motorista foi atribuido com sucesso!");

    }

}
