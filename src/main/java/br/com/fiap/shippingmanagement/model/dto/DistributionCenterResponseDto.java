package br.com.fiap.shippingmanagement.model.dto;

import br.com.fiap.shippingmanagement.model.DistributionCenter;

public record DistributionCenterResponseDto(
        String id,
        String nome
) {
    public DistributionCenterResponseDto(DistributionCenter distributionCenter) {
        this(distributionCenter.getId(), distributionCenter.getName());
    }
}
