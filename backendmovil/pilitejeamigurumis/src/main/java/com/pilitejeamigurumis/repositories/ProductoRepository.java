package com.pilitejeamigurumis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pilitejeamigurumis.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("select coalesce(sum(p.stock),0) from Producto p")
    Long sumStockTotal();
}
