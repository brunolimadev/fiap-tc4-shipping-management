package br.com.fiap.shippingmanagement.model.dto;

import br.com.fiap.shippingmanagement.model.Route;

public record RouteResponseDto(String id, String description) {
    public RouteResponseDto(Route route) {
        this(route.getId(), route.getDescription());
    }
}
