package com.pilitejeamigurumis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pilitejeamigurumis.entities.CarritoItem;

public interface CarritoItemRepository extends JpaRepository<CarritoItem, Long> {
}
