package com.pilitejeamigurumis.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pilitejeamigurumis.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    long countByRol(String rol);

    
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = 'CLIENTE'")
    long countClientes();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.rol = 'ADMIN'")
    long countAdmins();
}
