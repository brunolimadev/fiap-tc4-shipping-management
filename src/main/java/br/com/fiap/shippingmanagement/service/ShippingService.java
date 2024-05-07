package br.com.fiap.shippingmanagement.service;

import br.com.fiap.shippingmanagement.model.Driver;
import br.com.fiap.shippingmanagement.model.Shipping;
import br.com.fiap.shippingmanagement.model.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ShippingService {

    ResponseEntity<List<ShippingResponseDto>> findAllShipping();

    ResponseEntity<ShippingResponseDto> findShippingByShippingId(String shippingId);

    ResponseEntity<DriverResponseDto> saveDriver(DriverRequestDto driver);

    ResponseEntity<String> deleteDriverByDriverId(String driverId);

    ResponseEntity<ShippingResponseDto> assignDriverToShipment(String shippingId);

    ResponseEntity<ShippingResponseDto> saveShipping(ShippingRequestDto shipping);

    ResponseEntity<?> saveRoute(RouteRequestDto route);
}
