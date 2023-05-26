package com.delivery.api.Delivery.dto;

import lombok.Data;

@Data
public class EntregaDto {
    private Long id;
    private Long pedidoId;
    private String dataEntrega;
    private Long statusEntrega;
    private String enderecoEntrega;
}