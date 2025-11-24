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

import com.pilitejeamigurumis.entities.Categoria;
import com.pilitejeamigurumis.services.CategoriaService;

@RestController
@RequestMapping("api/categorias")
public class CategoriaRestController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> verCategorias() {
        return categoriaService.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verCategoria(@PathVariable Long id) {
        Optional<Categoria> c = categoriaService.findById(id);
        if (c.isPresent()) {
            return ResponseEntity.ok(c.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria c) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(c));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCategoria(@PathVariable Long id, @RequestBody Categoria c) {
        Optional<Categoria> cOpt = categoriaService.findById(id);
        if (cOpt.isPresent()) {
            Categoria categoria = cOpt.get();
            categoria.setNombre(c.getNombre());
            return ResponseEntity.ok(categoriaService.save(categoria));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable Long id) {
        Categoria c = new Categoria();
        c.setId(id);
        Optional<Categoria> cOpt = categoriaService.delete(c);
        if (cOpt.isPresent()) {
            return ResponseEntity.ok(cOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
