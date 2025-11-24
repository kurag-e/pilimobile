package com.pilitejeamigurumis.restcontrollers.client;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaDto;
import com.pilitejeamigurumis.services.BoletaService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClientBoletaController {

    private final BoletaService boletaService;

    public ClientBoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }

    @GetMapping("/boleta/{id}")
    public BoletaDto detalleBoleta(@PathVariable("id") Long id, Authentication auth) {
        return boletaService.obtenerDetalle(id);
    }
}
