package br.com.fiap.shippingmanagement.model.dto;

import br.com.fiap.shippingmanagement.model.Route;

import java.util.List;

public record RouteResponseDto(String id, String description, List<String> stepsRoute) {
    public RouteResponseDto(Route route) {
        this(route.getId(), route.getDescription(), route.getStepsRoute());
    }
}
