package br.com.fiap.shippingmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Client {
    private String id;
    private Address address;
    private String name;
    private String email;
}
