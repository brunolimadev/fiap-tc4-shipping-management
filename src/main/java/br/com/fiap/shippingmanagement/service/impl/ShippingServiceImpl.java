package br.com.fiap.shippingmanagement.service.impl;

import br.com.fiap.shippingmanagement.model.Driver;
import br.com.fiap.shippingmanagement.model.Shipping;
import br.com.fiap.shippingmanagement.model.dto.DriverRequestDto;
import br.com.fiap.shippingmanagement.model.dto.DriverResponseDto;
import br.com.fiap.shippingmanagement.repository.DriverRepository;
import br.com.fiap.shippingmanagement.repository.ShippingRepository;
import br.com.fiap.shippingmanagement.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public ResponseEntity<List<Shipping>> findAllShipping() {
        return null;
    }

    @Override
    public ResponseEntity<List<Shipping>> findShippingByShippingId(UUID shippingId) {
        return null;
    }

    @Override
    public ResponseEntity<DriverResponseDto> saveDriver(DriverRequestDto driver) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteDriverByDriverId(UUID driverId) {
        return null;
    }

    @Override
    public ResponseEntity<String> assignDriverToShipment(UUID shippingId) {
        return null;
    }
}
