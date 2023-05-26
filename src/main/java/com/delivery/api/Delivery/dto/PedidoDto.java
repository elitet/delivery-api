package com.delivery.api.Delivery.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoDto {
    private Long clienteId;
    private String dataPedido;
    private Double valorTotal;
    private Long status;
}
