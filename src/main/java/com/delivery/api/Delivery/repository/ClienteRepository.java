package com.delivery.api.Delivery.repository;

import com.delivery.api.Delivery.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

