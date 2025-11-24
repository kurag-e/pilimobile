package com.pilitejeamigurumis.services;

import com.pilitejeamigurumis.entities.*;
import com.pilitejeamigurumis.repositories.*;
import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaDto;
import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaResumenDto;
import com.pilitejeamigurumis.restcontrollers.dto.carrito.CarritoDto;
import com.pilitejeamigurumis.restcontrollers.dto.carrito.CheckoutRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepo;
    private final CarritoItemRepository itemRepo;
    private final ProductoRepository productoRepo;
    private final BoletaRepository boletaRepo;
    private final BoletaDetalleRepository detalleRepo;

    public CarritoService(CarritoRepository carritoRepo,
                          CarritoItemRepository itemRepo,
                          ProductoRepository productoRepo,
                          BoletaRepository boletaRepo,
                          BoletaDetalleRepository detalleRepo) {
        this.carritoRepo = carritoRepo;
        this.itemRepo = itemRepo;
        this.productoRepo = productoRepo;
        this.boletaRepo = boletaRepo;
        this.detalleRepo = detalleRepo;
    }

    @Transactional
    public CarritoDto getCarritoDtoForUser(Usuario u) {
        return carritoRepo.findByUsuario(u)
                .map(CarritoDto::fromEntity)
                .orElseGet(() -> new CarritoDto(Collections.emptyList()));
    }

    @Transactional
    public CarritoDto addItem(Usuario u, Long productoId, int cantidad) {
        Carrito carrito = carritoRepo.findByUsuario(u)
                .orElseGet(() -> {
                    Carrito c = new Carrito();
                    c.setUsuario(u);
                    c.setCreadoEn(LocalDateTime.now());
                    c.setActualizadoEn(LocalDateTime.now());
                    return carritoRepo.save(c);
                });

        Producto p = productoRepo.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        CarritoItem item = carrito.getItems().stream()
                .filter(i -> i.getProducto().getId().equals(productoId))
                .findFirst()
                .orElseGet(() -> {
                    CarritoItem n = new CarritoItem();
                    n.setCarrito(carrito);
                    n.setProducto(p);
                    n.setCantidad(0);
                    n.setPrecioUnit(p.getPrecio());
                    carrito.getItems().add(n);
                    return n;
                });

        item.setCantidad(item.getCantidad() + cantidad);
        carrito.setActualizadoEn(LocalDateTime.now());

        carritoRepo.save(carrito);
        return CarritoDto.fromEntity(carrito);
    }

    @Transactional
    public BoletaDto checkout(Usuario u, CheckoutRequest req) {
        Carrito carrito = carritoRepo.findByUsuario(u)
                .orElseThrow(() -> new RuntimeException("No hay carrito activo"));

        int subtotal = carrito.getItems().stream()
                .mapToInt(i -> i.getPrecioUnit() * i.getCantidad())
                .sum();
        int iva = (int) Math.round(subtotal * 0.19);
        int total = subtotal + iva;

        Long numero = Optional.ofNullable(boletaRepo.findMaxNumero())
                .map(n -> n + 1)
                .orElse(1L);

        Boleta b = new Boleta();
        b.setNumero(numero);
        b.setFecha(LocalDateTime.now());
        b.setSubtotal(subtotal);
        b.setIva(iva);
        b.setTotal(total);
        b.setUsuario(u);
        b.setNombre(req.getNombre());
        b.setEmail(req.getEmail());
        b.setRegion(req.getRegion());
        b.setComuna(req.getComuna());
        b.setDireccion(req.getDireccion());
        b.setEstadoPago("PAGADO");

        boletaRepo.save(b);

        for (CarritoItem ci : carrito.getItems()) {
            BoletaDetalle d = new BoletaDetalle();
            d.setBoleta(b);
            d.setProducto(ci.getProducto());
            d.setCantidad(ci.getCantidad());
            d.setPrecioUnit(ci.getPrecioUnit());
            d.setTotalLinea(ci.getPrecioUnit() * ci.getCantidad());

            detalleRepo.save(d);
            b.getDetalles().add(d);   
        }

        carrito.getItems().clear();
        carrito.setActualizadoEn(LocalDateTime.now());
        carritoRepo.save(carrito);

        return BoletaDto.fromEntity(b);
    }

    @Transactional(readOnly = true)
    public List<BoletaResumenDto> getHistorial(Usuario u) {
        return boletaRepo.findByUsuarioOrderByFechaDesc(u).stream()
                .map(BoletaResumenDto::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<BoletaResumenDto> getTodasBoletas() {
        return boletaRepo.findAllByOrderByFechaDesc().stream()
                .map(BoletaResumenDto::fromEntity)
                .toList();
    }
}
