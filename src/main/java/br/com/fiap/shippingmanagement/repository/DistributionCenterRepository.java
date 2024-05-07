package br.com.fiap.shippingmanagement.repository;

import br.com.fiap.shippingmanagement.model.DistributionCenter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DistributionCenterRepository extends MongoRepository<DistributionCenter, String> {
    List<DistributionCenter> findDistributionCenterByAddressProvince(String province);
}
