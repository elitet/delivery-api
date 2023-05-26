package com.delivery.api.Delivery.repository;

import com.delivery.api.Delivery.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findByEmail(String email);

}
