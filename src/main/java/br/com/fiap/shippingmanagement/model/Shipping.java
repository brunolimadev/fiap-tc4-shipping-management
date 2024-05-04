package br.com.fiap.shippingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@Document("shipping")
public class Shipping {

    @Id
    private UUID id;
    private UUID client_id;
    private UUID order_id;

}
