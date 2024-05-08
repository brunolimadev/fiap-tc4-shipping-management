package br.com.fiap.shippingmanagement.controller;

import br.com.fiap.shippingmanagement.model.dto.*;
import br.com.fiap.shippingmanagement.service.ShippingService;
import com.google.maps.errors.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/shippings")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping
    public ResponseEntity<?> findAllShipping() {
        return ResponseEntity.ok(shippingService.findAllShipping());
    }

    @GetMapping("/{shipping_id}")
    public ResponseEntity<?> findShippingByShippingId(@PathVariable("shipping_id") String shippingId) {
        return ResponseEntity.ok(shippingService.findShippingByShippingId(shippingId));
    }

    @PostMapping()
    public ResponseEntity<ShippingResponseDto> saveShipping(@RequestBody ShippingRequestDto shipping) {
        return ResponseEntity.ok(shippingService.saveShipping(shipping));
    }

    @PostMapping("/drivers")
    public ResponseEntity<?> saveDriver(@RequestBody DriverRequestDto driver) {
        return ResponseEntity.ok(shippingService.saveDriver(driver));
    }

    @DeleteMapping("/drivers/{driver_id}")
    public ResponseEntity<?> deleteDriver(@PathVariable("driver_id") String driverId) {
        return ResponseEntity.ok(shippingService.deleteDriverByDriverId(driverId));
    }

    @PutMapping("/{shipping_id}/finish_delivery/{finish_datetime}")
    public ResponseEntity<?> finishDelivery(@PathVariable("shipping_id") String shipping_id,
                                            @PathVariable("finish_datetime") LocalDateTime finishDate) {
        return ResponseEntity.ok(shippingService.finishDeliveryByShippingId(shipping_id, finishDate));
    }

    @PutMapping("/{shipping_id}/assign_driver")
    public ResponseEntity<?> assignDriverToShipment(@PathVariable("shipping_id") String shippingId) throws IOException, InterruptedException, ApiException {
        return ResponseEntity.ok(shippingService.assignDriverToShipment(shippingId));
    }

    @PostMapping("/routes")
    public ResponseEntity<?> saveRoute(@RequestBody RouteRequestDto route) {
        return ResponseEntity.ok(shippingService.saveRoute(route));
    }

    @PostMapping("/distributions_centers")
    public ResponseEntity<?> saveDistributionCenter(@RequestBody DistributionCenterRequestDto distributionCenter) {
        return ResponseEntity.ok(shippingService.saverDistributionCenter(distributionCenter));
    }

}
