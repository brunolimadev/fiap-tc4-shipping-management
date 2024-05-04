package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route, String> {
}
