package br.com.fiap.shippingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@Document("address")
public class Address {

    @Id
    private String id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String province;
    private String country;

    @Override
    public String toString() {
        return this.street
                + ", nº " + this.number
                + ", " + this.complement
                + ", " + this.neighborhood
                + ", " + this.city
                + ", " + this.province
                + ", " + this.country;
    }

}
