package br.com.fiap.shippingmanagement.service;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsService {

    @Value("google.maps.app-key")
    private String googleMapsKey;

    private final GeoApiContext geoApiContext;

    public GoogleMapsService() {
        this.geoApiContext = new GeoApiContext.Builder()
                .apiKey(googleMapsKey)
                .build();
    }

    public DirectionsResult getDirections(String origin, String destination) throws ApiException, InterruptedException, IOException {
        return DirectionsApi.newRequest(geoApiContext)
                .origin(origin)
                .destination(destination)
                .mode(TravelMode.DRIVING)
                .await();
    }
}
