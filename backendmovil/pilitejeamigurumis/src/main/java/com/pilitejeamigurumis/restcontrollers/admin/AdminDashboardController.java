package com.pilitejeamigurumis.restcontrollers.admin;

import com.pilitejeamigurumis.repositories.*;
import com.pilitejeamigurumis.restcontrollers.dto.admin.AdminStatsDto;
import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaResumenDto;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminDashboardController {

    private final UsuarioRepository usuarioRepo;
    private final ProductoRepository productoRepo;
    private final BoletaRepository boletaRepo;

    public AdminDashboardController(UsuarioRepository usuarioRepo,
                                    ProductoRepository productoRepo,
                                    BoletaRepository boletaRepo) {
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.boletaRepo = boletaRepo;
    }

    @GetMapping("/stats")
    public AdminStatsDto getStats() {
        AdminStatsDto dto = new AdminStatsDto();
        dto.setTotalUsuarios(usuarioRepo.count());
        dto.setTotalClientes(usuarioRepo.countByRol("CLIENTE"));
        dto.setTotalAdmins(usuarioRepo.countByRol("ADMIN"));
        dto.setTotalVendedores(usuarioRepo.countByRol("VENDEDOR"));
        dto.setTotalProductos(productoRepo.count());
        dto.setStockTotal(productoRepo.sumStockTotal());
        dto.setTotalBoletas(boletaRepo.count());
        dto.setTotalVentas(boletaRepo.sumTotalVentas());
        return dto;
    }

    @GetMapping("/boletas/recientes")
    public List<BoletaResumenDto> ultimasBoletas() {
        return boletaRepo.findTop10ByOrderByFechaDesc().stream()
                .map(BoletaResumenDto::fromEntity)
                .toList();
    }
}
