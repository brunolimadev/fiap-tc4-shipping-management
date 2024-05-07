package br.com.fiap.shippingmanagement.service;

import br.com.fiap.shippingmanagement.model.dto.*;

import java.util.List;

public interface ShippingService {

    List<ShippingResponseDto> findAllShipping();

    ShippingResponseDto findShippingByShippingId(String shippingId);

    DriverResponseDto saveDriver(DriverRequestDto driver);

    String deleteDriverByDriverId(String driverId);

    ShippingResponseDto assignDriverToShipment(String shippingId);

    ShippingResponseDto saveShipping(ShippingRequestDto shipping);

    RouteResponseDto saveRoute(RouteRequestDto route);

    DistributionCenterResponseDto saverDistributionCenter(DistributionCenterRequestDto distributionCenter);
}
