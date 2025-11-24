package com.pilitejeamigurumis.services;

import java.util.List;
import java.util.Optional;

import com.pilitejeamigurumis.entities.Producto;

public interface ProductoService {
    List<Producto> findByAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    Optional<Producto> delete(Producto producto);
}
