package br.com.fiap.shippingmanagement.service.impl;

import br.com.fiap.shippingmanagement.exception.ExceptionShippingValidation;
import br.com.fiap.shippingmanagement.model.*;
import br.com.fiap.shippingmanagement.model.dto.*;
import br.com.fiap.shippingmanagement.repository.*;
import br.com.fiap.shippingmanagement.service.GoogleMapsService;
import br.com.fiap.shippingmanagement.service.ShippingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Value("${ms-client.url}")
    private String url;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private ShippingDriverRepository shippingDriverRepository;

    @Autowired
    private DistributionCenterRepository distributionCenterRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GoogleMapsService googleMapsService;

    @Override
    public List<ShippingResponseDto> findAllShipping() {
        return shippingRepository.findAll()
                .stream()
                .map(ShippingResponseDto::new)
                .toList();
    }

    @Override
    public ShippingResponseDto findShippingByShippingId(String shippingId) {
        return shippingRepository.findById(shippingId)
                .map(ShippingResponseDto::new)
                .orElse(null);
    }

    @Override
    public DriverResponseDto saveDriver(DriverRequestDto driver) {
        var newAddress = addressRepository.save(createAddressByAddressDto(driver.address()));
        var newDriver = createDriverByDriverDto(driver);
        newDriver.setAddress(newAddress);
        return new DriverResponseDto(driverRepository.save(newDriver));
    }

    @Override
    public String deleteDriverByDriverId(String driverId) {
        driverRepository.deleteById(driverId);
        return "Motorista deletado com sucesso.";
    }

    @Override
    public ShippingResponseDto assignDriverToShipment(String shippingId) throws IOException, InterruptedException, ApiException {

        var shipping = shippingRepository.findById(shippingId).orElse(null);
        var driverSelected = defineBestDriver(driverRepository.findAll());
        var distributionCenterSelected = defineBestDistributionCenter(distributionCenterRepository.findAll());

        if (shipping == null) {
            throw new ExceptionShippingValidation("Pedido de logistica não encontrado");
        }

        if (driverSelected == null) {
            throw new ExceptionShippingValidation("Não encontramos motoritas disponíveis!");
        }

        if (distributionCenterSelected == null) {
            throw new ExceptionShippingValidation("Centro de distribuição não encontrado!");
        }

        var origin = distributionCenterSelected.getAddress();
        var destination = getDeliveryAddressByClientId(shipping.getClient_id());

        var routeSelected = this.criateRoute(
                creteRouteDescription(shipping, driverSelected),
                origin.toString(),
                destination.toString()
        );

        if (routeSelected == null) {
            throw new ExceptionShippingValidation("Não encontramos uma rota para esse pedido!");
        }

        var shippingDriver = ShippingDriver.builder()
                .shipping(shipping)
                .route(routeSelected)
                .driver(driverSelected)
                .sende_address(origin)
                .delivery_address(destination)
                .start_delivery(LocalDateTime.now())
                .build();

        return new ShippingResponseDto(shipping, shippingDriverRepository.save(shippingDriver));
    }

    private String creteRouteDescription(Shipping shipping, Driver driverSelected) {
        return String.format("DRIVER_NAME:%s;CLIENT_ID:%s;ORDER_ID:%s;DRIVER_ID:%s",
                driverSelected.getName(),
                shipping.getClient_id(),
                shipping.getOrder_id(),
                driverSelected.getId()
        );
    }

    @Override
    public ShippingResponseDto saveShipping(ShippingRequestDto shipping) {
        var newShipping = Shipping.builder()
                .client_id(shipping.clientId())
                .order_id(shipping.orderId())
                .build();
        return new ShippingResponseDto(shippingRepository.save(newShipping));
    }

    @Override
    public RouteResponseDto saveRoute(RouteRequestDto route) {
        var newRoute = Route.builder()
                .description(route.description())
                .build();
        return new RouteResponseDto(routeRepository.save(newRoute));
    }

    @Override
    public Route criateRoute(String routeName, String origin, String destination) throws IOException, InterruptedException, ApiException {
        var newRoute = Route.builder()
                .description(routeName)
                // TODO::: Preocisa testar essa converção de uma lista de objetos para uma lista de strings
                .stepsRoute(Arrays.stream(googleMapsService.getDirections(origin, destination).routes).map(String::valueOf).toList())
                .build();
        return routeRepository.save(newRoute);
    }

    @Override
    public DistributionCenterResponseDto saverDistributionCenter(DistributionCenterRequestDto distributionCenter) {
        var newDistributionCenter = creteDistributionCenterByDistributionCenterRequestDto(distributionCenter);
        var newAddress = newDistributionCenter.getAddress();
        newAddress = addressRepository.save(newAddress);
        return new DistributionCenterResponseDto(distributionCenterRepository.save(newDistributionCenter));
    }

    @Override
    public String finishDeliveryByShippingId(String shippingId, String finishDate) {
        // TODO ::: Não tenho certeza se o SHIPPING_ID é a melhor opção para essa busca, precisa testar
        var shippingDriver = shippingDriverRepository.findByShippingId(shippingId);

        if (shippingDriver == null) {
            throw new ExceptionShippingValidation("Pedido de logistica não encontrado");
        }

        shippingDriver.setFinish_delivery(LocalDateTime.now());
        shippingDriverRepository.save(shippingDriver);
        return "Entrega finalizada com sucesso";
    }

    private Address getDeliveryAddressByClientId(String clientId) {

//        ResponseEntity<Client> response = restTemplate.getForEntity(
//                String.format("%s/{client_d}", url),
//                Client.class,
//                clientId
//        );
//
//        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
//            throw new NoSuchElementException("Cliente não encontrato");
//        }
//
//        if (response.getBody() == null) {
//            throw new NoSuchElementException("Endereço de entrega não encontrado");
//        }
//
//        return response.getBody().getAddress();

        return Address.builder()
                .id("f1ed3106-93d5-4990-93b6-08334dc56664")
                .number("46")
                .street("Av. Princesa Januaria")
                .city("São Bernardo do Campo")
                .province("SP")
                .complement("")
                .country("Brazil")
                .build();

    }

    private DistributionCenter creteDistributionCenterByDistributionCenterRequestDto(DistributionCenterRequestDto distributionCenter) {
        return DistributionCenter.builder()
                .name(distributionCenter.nome())
                .address(createAddressByAddressDto(distributionCenter.address()))
                .build();
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

    public static Driver defineBestDriver(List<Driver> list) {
        return selectRandomObject(list);
    }

    private DistributionCenter defineBestDistributionCenter(List<DistributionCenter> list) {
        return selectRandomObject(list);
    }

    public static <T> T selectRandomObject(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
