package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DriverRepository extends MongoRepository<Driver, UUID> {
}
