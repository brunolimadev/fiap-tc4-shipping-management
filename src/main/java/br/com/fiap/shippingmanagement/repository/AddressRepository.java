package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AddressRepository extends MongoRepository<Address, UUID> {
}
