package com.delivery.api.Delivery.repository;

import com.delivery.api.Delivery.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

