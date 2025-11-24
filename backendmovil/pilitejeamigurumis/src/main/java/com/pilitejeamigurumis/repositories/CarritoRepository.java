package com.pilitejeamigurumis.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pilitejeamigurumis.entities.Carrito;
import com.pilitejeamigurumis.entities.Usuario;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    Optional<Carrito> findByUsuario(Usuario usuario);

    Optional<Carrito> findBySessionId(String sessionId);
}
