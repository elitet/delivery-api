package com.delivery.api.Delivery.repository;

import com.delivery.api.Delivery.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}

