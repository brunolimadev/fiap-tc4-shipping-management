package br.com.fiap.shippingmanagement.model.dto;

import br.com.fiap.shippingmanagement.model.Shipping;
import br.com.fiap.shippingmanagement.model.ShippingDriver;

import java.util.List;

public record ShippingResponseDto(
        String id,
        String clientId,
        String orderId,
        String driverName,
        String vehiclePlate,
        String shippingDriverId,
        String routeDescription,
        List<String> route
) {
    public ShippingResponseDto(Shipping shipping) {
        this(
                shipping.getId(),
                shipping.getClient_id(),
                shipping.getOrder_id(),
                null,
                null,
                null,
                null,
                null
                );
    }

    public ShippingResponseDto(Shipping shipping, ShippingDriver shippingDriver) {
        this(
                shipping.getId(),
                shipping.getClient_id(),
                shipping.getOrder_id(),
                shippingDriver.getDriver().getName(),
                shippingDriver.getDriver().getVehicle_plate(),
                shippingDriver.getId(),
                shippingDriver.getRoute().getDescription(),
                shippingDriver.getRoute().getStepsRoute()
        );
    }
}
