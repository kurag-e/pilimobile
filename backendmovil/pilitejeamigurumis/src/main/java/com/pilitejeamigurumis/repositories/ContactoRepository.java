package com.pilitejeamigurumis.repositories;

import com.pilitejeamigurumis.entities.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
