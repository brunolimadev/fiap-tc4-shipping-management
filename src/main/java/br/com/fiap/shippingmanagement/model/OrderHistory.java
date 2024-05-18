package br.com.fiap.shippingmanagement.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.fiap.shippingmanagement.enumarators.StatusEnum;
import lombok.Builder;
import lombok.Data;

@Document(collection = "orders_history")
@Data
@Builder
public class OrderHistory {

    private String id;

    private String orderId;

    private String description;

    private StatusEnum status;

    @CreatedDate
    private String createdAt;

}
