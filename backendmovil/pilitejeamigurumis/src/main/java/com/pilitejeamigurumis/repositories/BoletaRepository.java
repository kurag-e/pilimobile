package com.pilitejeamigurumis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pilitejeamigurumis.entities.Boleta;
import com.pilitejeamigurumis.entities.Usuario;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta, Long> {

    @Query("select max(b.numero) from Boleta b")
    Long findMaxNumero();

    List<Boleta> findByUsuarioOrderByFechaDesc(Usuario usuario);

    List<Boleta> findAllByOrderByFechaDesc();

    // boletas recientes para dashboard
    List<Boleta> findTop10ByOrderByFechaDesc();

    // total acumulado de ventas (suma del campo 'total' de la boleta)
    @Query("select coalesce(sum(b.total), 0) from Boleta b")
    Long sumTotalVentas();

    long countByUsuario(Usuario usuario);
}
