package br.com.fiap.shippingmanagement.model.dto;

import br.com.fiap.shippingmanagement.model.Driver;
import br.com.fiap.shippingmanagement.model.Shipping;

public record ShippingResponseDto(
        String id,
        String clientId,
        String orderId,
        String driverId,
        String driverName,
        String vehiclePlate
) {
    public ShippingResponseDto(Shipping shipping) {
        this(
                shipping.getId(),
                shipping.getClient_id(),
                shipping.getOrder_id(),
                shipping.getDriver_id(),
                null,
                null
        );
    }
    public ShippingResponseDto(Shipping shipping, Driver driver) {
        this(
                shipping.getId(),
                shipping.getClient_id(),
                shipping.getOrder_id(),
                shipping.getDriver_id(),
                driver.getName(),
                driver.getVehicle_plate()
        );
    }
}
