package com.pilitejeamigurumis.services;

import java.util.List;
import java.util.Optional;

import com.pilitejeamigurumis.entities.Categoria;

public interface CategoriaService {
    List<Categoria> findByAll();
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
    Optional<Categoria> delete(Categoria categoria);
}
