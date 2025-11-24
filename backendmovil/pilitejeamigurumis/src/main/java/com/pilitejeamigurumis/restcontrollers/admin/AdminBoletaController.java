package com.pilitejeamigurumis.restcontrollers.admin;

import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaDto;
import com.pilitejeamigurumis.services.BoletaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/boletas")
public class AdminBoletaController {

    private final BoletaService boletaService;

    public AdminBoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoletaDto> getBoleta(@PathVariable("id") Long id) {
        BoletaDto dto = boletaService.obtenerDetalle(id);
        return ResponseEntity.ok(dto);
    }
}

