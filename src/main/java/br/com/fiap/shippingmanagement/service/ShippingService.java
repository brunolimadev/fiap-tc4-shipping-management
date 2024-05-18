package br.com.fiap.shippingmanagement.service;

import br.com.fiap.shippingmanagement.model.Route;
import br.com.fiap.shippingmanagement.model.dto.*;
import com.google.maps.errors.ApiException;

import java.io.IOException;
import java.util.List;

public interface ShippingService {

    List<ShippingResponseDto> findAllShipping();

    ShippingResponseDto findShippingByShippingId(String shippingId);

    DriverResponseDto saveDriver(DriverRequestDto driver);

    String deleteDriverByDriverId(String driverId);

    ShippingResponseDto assignDriverToShipment(String shippingId)
            throws IOException, InterruptedException, ApiException;

    ShippingResponseDto saveShipping(ShippingRequestDto shipping);

    RouteResponseDto saveRoute(RouteRequestDto route);

    DistributionCenterResponseDto saverDistributionCenter(DistributionCenterRequestDto distributionCenter);

    String finishDeliveryByShippingId(String shippingId, String finishDate);

    Route criateRoute(String routeName, String origin, String destination)
            throws IOException, InterruptedException, ApiException;

    List<DriverResponseDto> getAllDrivers();
}
