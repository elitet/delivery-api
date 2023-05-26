package com.delivery.api.Delivery.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@Data
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @Column(name = "status_entrega")
    private Long statusEntrega;

    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

}

