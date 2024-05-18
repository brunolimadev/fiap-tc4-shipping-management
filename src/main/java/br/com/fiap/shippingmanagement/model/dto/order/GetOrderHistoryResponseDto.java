package br.com.fiap.shippingmanagement.model.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderHistoryResponseDto {
    private String orderId;
    private String description;
    private String status;
    private String createdAt;
    private String clientId;

    @Override
    public String toString() {
        return "{" +
                "orderId='" + orderId + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
