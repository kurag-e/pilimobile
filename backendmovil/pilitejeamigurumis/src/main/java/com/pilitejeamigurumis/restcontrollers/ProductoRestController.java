package com.pilitejeamigurumis.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilitejeamigurumis.entities.Producto;
import com.pilitejeamigurumis.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> verProductos() {
        return productoService.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verProducto(@PathVariable("id") Long id) {
        return productoService.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto p) {
        if (p.getNombre() == null || p.getNombre().isBlank())
            return ResponseEntity.badRequest().body("Nombre es obligatorio");
        if (p.getPrecio() == null)
            return ResponseEntity.badRequest().body("Precio es obligatorio");
        if (p.getStock() == null)
            return ResponseEntity.badRequest().body("Stock es obligatorio");
        if (p.getEstado() == null || p.getEstado().isBlank())
            p.setEstado("ACTIVO");
        if (p.getStockCritico() == null)
            p.setStockCritico(0);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable("id") Long id,
                                               @RequestBody Producto p) {
        return productoService.findById(id).map(prod -> {
            if (p.getNombre() != null)        prod.setNombre(p.getNombre());
            if (p.getDescripcion() != null)   prod.setDescripcion(p.getDescripcion());
            if (p.getPrecio() != null)        prod.setPrecio(p.getPrecio());
            if (p.getStock() != null)         prod.setStock(p.getStock());
            if (p.getStockCritico() != null)  prod.setStockCritico(p.getStockCritico());
            if (p.getCategoria() != null)     prod.setCategoria(p.getCategoria());
            if (p.getEstado() != null)        prod.setEstado(p.getEstado());
            if (p.getImagen() != null)        prod.setImagen(p.getImagen());
            if (p.getFechaCreacion() != null) prod.setFechaCreacion(p.getFechaCreacion());
            return ResponseEntity.ok(productoService.save(prod));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable("id") Long id) {
        Producto p = new Producto();
        p.setId(id);
        Optional<Producto> pOpt = productoService.delete(p);
        return pOpt.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
