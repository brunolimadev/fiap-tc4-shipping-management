package br.com.fiap.shippingmanagement.model.dto;

import br.com.fiap.shippingmanagement.model.Driver;

public record DriverResponseDto(
        String name,
        String vehiclePlate,
        String email,
        String phone
) {
    public DriverResponseDto(Driver driver) {
        this(
                driver.getName(),
                driver.getVehicle_plate(),
                driver.getEmail(),
                driver.getPhone()
        );
    }
}
