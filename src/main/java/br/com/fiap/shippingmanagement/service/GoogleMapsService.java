package br.com.fiap.shippingmanagement.service;

import br.com.fiap.shippingmanagement.config.GoogleMapsProperties;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsService {

    private final GeoApiContext geoApiContext;

    @Autowired
    public GoogleMapsService(GoogleMapsProperties googleMapsProperties) {
        this.geoApiContext = new GeoApiContext.Builder()
                .apiKey(googleMapsProperties.getAppKey())
                .build();
    }

    public DirectionsResult getDirections(String origin, String destination)
            throws ApiException, InterruptedException, IOException {
        return DirectionsApi.newRequest(geoApiContext)
                .origin(origin)
                .destination(destination)
                .mode(TravelMode.DRIVING)
                .await();
    }
}
