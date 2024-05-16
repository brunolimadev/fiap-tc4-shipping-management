package br.com.fiap.shippingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document("driver")
public class Driver {

    @Id
    private String id;

    @DBRef
    private Address address;

    private String name;
    private String vehicle_plate;
    private String email;
    private String phone;

}
