package br.com.fiap.shippingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Document("diver")
public class Driver {

    @Id
    private UUID id;

    @DBRef
    private Address address;

    private String name;
    private String vehicle_plate;
    private String email;
    private String phone;

}
