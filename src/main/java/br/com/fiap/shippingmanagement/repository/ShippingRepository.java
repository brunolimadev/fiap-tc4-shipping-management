package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.Shipping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingRepository extends MongoRepository<Shipping, String> {
}
