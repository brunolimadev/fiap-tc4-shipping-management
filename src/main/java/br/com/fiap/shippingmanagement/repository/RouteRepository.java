package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RouteRepository extends MongoRepository<Route, UUID> {
}
