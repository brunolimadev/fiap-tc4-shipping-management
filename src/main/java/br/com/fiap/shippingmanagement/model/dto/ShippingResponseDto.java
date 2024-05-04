package br.com.fiap.shippingmanagement.model.dto;

import br.com.fiap.shippingmanagement.model.Shipping;

public record ShippingResponseDto(String id, String clientId, String orderId) {
    public ShippingResponseDto(Shipping shipping) {
        this(
                shipping.getId().toString(),
                shipping.getClient_id().toString(),
                shipping.getOrder_id().toString()
        );
    }
}
