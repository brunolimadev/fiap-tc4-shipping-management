package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.Shipping;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ShippingRepository extends MongoRepository<Shipping, UUID> {
}
