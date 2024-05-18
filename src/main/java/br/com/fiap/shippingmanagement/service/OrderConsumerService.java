package br.com.fiap.shippingmanagement.service;

import br.com.fiap.shippingmanagement.model.dto.order.GetOrderHistoryResponseDto;

public interface OrderConsumerService {

    void consume(GetOrderHistoryResponseDto orderHistoryResponseDto);

}
