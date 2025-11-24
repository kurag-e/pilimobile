package com.pilitejeamigurumis.services;

import com.pilitejeamigurumis.entities.Boleta;
import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.repositories.BoletaRepository;
import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaDto;
import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaResumenDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BoletaService {

    private final BoletaRepository boletaRepo;

    public BoletaService(BoletaRepository boletaRepo) {
        this.boletaRepo = boletaRepo;
    }

    @Transactional(readOnly = true)
    public List<BoletaResumenDto> listarTodas() {
        return boletaRepo.findAllByOrderByFechaDesc()
                .stream()
                .map(BoletaResumenDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
public BoletaDto obtenerDetalle(Long id) {
    Boleta b = boletaRepo.findById(id)
            .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Boleta no encontrada"));
    return BoletaDto.fromEntity(b);
}


    @Transactional(readOnly = true)
    public List<BoletaResumenDto> historialPorUsuario(Usuario u) {
        return boletaRepo.findByUsuarioOrderByFechaDesc(u)
                .stream()
                .map(BoletaResumenDto::fromEntity)
                .toList();
    }
}
