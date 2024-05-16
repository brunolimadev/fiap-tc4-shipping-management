package br.com.fiap.shippingmanagement.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("distribution_center")
public class DistributionCenter {

    @Id
    private String id;

    private String name;

    @DBRef
    private Address address;
}
