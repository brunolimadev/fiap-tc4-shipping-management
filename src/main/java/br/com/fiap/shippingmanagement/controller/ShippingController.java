package br.com.fiap.shippingmanagement.controller;

import br.com.fiap.shippingmanagement.model.dto.DriverRequestDto;
import br.com.fiap.shippingmanagement.model.dto.RouteRequestDto;
import br.com.fiap.shippingmanagement.model.dto.ShippingRequestDto;
import br.com.fiap.shippingmanagement.model.dto.ShippingResponseDto;
import br.com.fiap.shippingmanagement.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shippings")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping
    public ResponseEntity<?> findAllShipping() {
        return shippingService.findAllShipping();
    }

    @GetMapping("/{shipping_id}")
    public ResponseEntity<?> findShippingByShippingId(@PathVariable("shipping_id") String shippingId) {
        return shippingService.findShippingByShippingId(shippingId);
    }

    @PostMapping()
    public ResponseEntity<ShippingResponseDto> saveShipping(@RequestBody ShippingRequestDto shipping) {
        return shippingService.saveShipping(shipping);
    }

    @PostMapping("/drivers")
    public ResponseEntity<?> saveDriver(@RequestBody DriverRequestDto driver) {
        return shippingService.saveDriver(driver);
    }

    @DeleteMapping("/drivers/{driver_id}")
    public ResponseEntity<?> deleteDriver(@PathVariable("driver_id") String driverId) {
        return shippingService.deleteDriverByDriverId(driverId);
    }

    @PutMapping("/{shipping_id}")
    public ResponseEntity<?> assignDriverToShipment(@PathVariable("shipping_id") String shippingId) {
        return shippingService.assignDriverToShipment(shippingId);
    }

    @PostMapping("/routes")
    public ResponseEntity<?> saveRoute(@RequestBody RouteRequestDto route) {
        return shippingService.saveRoute(route);
    }

}
