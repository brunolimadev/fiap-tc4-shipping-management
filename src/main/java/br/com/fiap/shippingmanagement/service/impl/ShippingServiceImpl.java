package br.com.fiap.shippingmanagement.service.impl;

import br.com.fiap.shippingmanagement.exception.ExceptionShippingValidation;
import br.com.fiap.shippingmanagement.model.Address;
import br.com.fiap.shippingmanagement.model.Driver;
import br.com.fiap.shippingmanagement.model.Shipping;
import br.com.fiap.shippingmanagement.model.dto.*;
import br.com.fiap.shippingmanagement.repository.AddressRepository;
import br.com.fiap.shippingmanagement.repository.DriverRepository;
import br.com.fiap.shippingmanagement.repository.ShippingRepository;
import br.com.fiap.shippingmanagement.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public ResponseEntity<List<ShippingResponseDto>> findAllShipping() {
        return ResponseEntity.ok(
                shippingRepository.findAll()
                        .stream()
                        .map(ShippingResponseDto::new)
                        .toList()
        );
    }

    @Override
    public ResponseEntity<ShippingResponseDto> findShippingByShippingId(UUID shippingId) {
        return ResponseEntity.ok(
                shippingRepository.findById(shippingId)
                        .map(ShippingResponseDto::new)
                        .orElse(null)
        );
    }

    @Override
    public ResponseEntity<DriverResponseDto> saveDriver(DriverRequestDto driver) {
        var newAddress = addressRepository.save(createAddressByAddressDto(driver.address()));
        var newDriver = createDriverByDriverDto(driver);
        newDriver.setAddress(newAddress);
        return ResponseEntity.ok(  new DriverResponseDto( driverRepository.save(newDriver)));
    }

    @Override
    public ResponseEntity<String> deleteDriverByDriverId(UUID driverId) {
        driverRepository.deleteById(driverId);
        return ResponseEntity.ok("Motorista deletado com sucesso.");
    }

    @Override
    public ResponseEntity<String> assignDriverToShipment(UUID shippingId) {
        List<Driver> drivers = driverRepository.findAll();
        var driverSelected = selectRandomDriver(drivers);
        Shipping shipping = shippingRepository.findById(shippingId).orElse(null);

        if (driverSelected == null ||  driverSelected.getId() == null) {
            throw new ExceptionShippingValidation("Não encontramos motoritas disponíveis!");
        }

        if (shipping == null) {
            throw new ExceptionShippingValidation("Pedido de logistica não encontrado");
        }

        shipping.setDriver_id(driverSelected.getId());
        shippingRepository.save(shipping);

        return ResponseEntity.ok("Motorista atribuido com sucesso.");
    }

    @Override
    public ResponseEntity<ShippingResponseDto> saveShipping(ShippingRequestDto shipping) {
        var newShipping = Shipping.builder()
                .client_id(shipping.clientId())
                .order_id(shipping.orderId())
                .build();
        return ResponseEntity.ok(new ShippingResponseDto(shippingRepository.save(newShipping)));
    }

    private static Driver createDriverByDriverDto(DriverRequestDto driverDto) {

        return Driver.builder()
                .name(driverDto.name())
                .email(driverDto.email())
                .phone(driverDto.phone())
                .vehicle_plate(driverDto.vehiclePlate())
                .build();
    }

    private static Address createAddressByAddressDto(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.street())
                .number(addressDto.number())
                .complement(addressDto.complement())
                .neighborhood(addressDto.neighborhood())
                .city(addressDto.city())
                .province(addressDto.province())
                .country(addressDto.country())
                .build();
    }

    public static Driver selectRandomDriver(List<Driver> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
