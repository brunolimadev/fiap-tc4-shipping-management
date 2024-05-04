package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.ShippingDriver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingDriverRepository extends MongoRepository<ShippingDriver, String> {
}
