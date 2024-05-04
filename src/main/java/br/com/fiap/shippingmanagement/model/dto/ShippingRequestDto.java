package br.com.fiap.shippingmanagement.model.dto;

import java.util.UUID;

public record ShippingRequestDto(UUID clientId, UUID orderId) {
}
