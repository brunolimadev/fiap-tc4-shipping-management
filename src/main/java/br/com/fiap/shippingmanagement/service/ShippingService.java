package br.com.fiap.shippingmanagement.service;

import br.com.fiap.shippingmanagement.model.Driver;
import br.com.fiap.shippingmanagement.model.Shipping;
import br.com.fiap.shippingmanagement.model.dto.DriverRequestDto;
import br.com.fiap.shippingmanagement.model.dto.DriverResponseDto;
import br.com.fiap.shippingmanagement.model.dto.ShippingRequestDto;
import br.com.fiap.shippingmanagement.model.dto.ShippingResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ShippingService {

    ResponseEntity<List<ShippingResponseDto>> findAllShipping();

    ResponseEntity<ShippingResponseDto> findShippingByShippingId(UUID shippingId);

    ResponseEntity<DriverResponseDto> saveDriver(DriverRequestDto driver);

    ResponseEntity<String> deleteDriverByDriverId(UUID driverId);

    ResponseEntity<String> assignDriverToShipment(UUID shippingId);

    ResponseEntity<ShippingResponseDto> saveShipping(ShippingRequestDto shipping);
}
