package br.com.fiap.shippingmanagement.model.dto;

public record AddressDto(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String province,
        String country
) {
}
