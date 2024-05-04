package br.com.fiap.shippingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("shipping_driver")
public class ShippingDriver {

    @Id
    private String id;

    @DBRef
    private Shipping shipping;

    @DBRef
    private Route route;

    @DBRef
    private Driver driver;

}
