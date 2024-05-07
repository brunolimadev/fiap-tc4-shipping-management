package br.com.fiap.shippingmanagement.model.dto;

public record DistributionCenterRequestDto(
        String nome,
        AddressDto address
) {
}
