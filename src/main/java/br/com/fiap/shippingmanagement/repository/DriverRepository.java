package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<Driver, String> {
}
