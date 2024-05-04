package br.com.fiap.shippingmanagement.model.dto;

public record DriverRequestDto(
        String name,
        String email,
        String phone,
        String vehiclePlate,
        AddressDto address
) {
}
