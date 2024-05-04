package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.ShippingDriver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ShippingDriverRepository extends MongoRepository<ShippingDriver, UUID> {
}
