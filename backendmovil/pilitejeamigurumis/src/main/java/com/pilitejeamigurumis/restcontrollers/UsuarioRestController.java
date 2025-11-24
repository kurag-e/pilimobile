package com.pilitejeamigurumis.restcontrollers;

import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> verUsuarios() {
        return usuarioService.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verUsuario(@PathVariable("id") Long id) {
        Optional<Usuario> u = usuarioService.findById(id);
        return u.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario u) {
        if (u.getNombre() == null || u.getNombre().isBlank()
                || u.getEmail() == null || u.getEmail().isBlank()
                || u.getPassword() == null || u.getPassword().isBlank()
                || u.getRol() == null || u.getRol().isBlank()
                || u.getEstado() == null || u.getEstado().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Faltan campos obligatorios (incluye pass).");
        }
        Usuario guardado = usuarioService.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("id") Long id,
                                              @RequestBody Usuario u) {
        Optional<Usuario> uOpt = usuarioService.findById(id);
        if (uOpt.isEmpty()) return ResponseEntity.notFound().build();

        Usuario usuario = uOpt.get();

        if (u.getNombre() != null) usuario.setNombre(u.getNombre());
        if (u.getEmail() != null) usuario.setEmail(u.getEmail());
        if (u.getPassword() != null && !u.getPassword().isBlank())
            usuario.setPassword(u.getPassword());
        if (u.getRol() != null) usuario.setRol(u.getRol());
        if (u.getEstado() != null) usuario.setEstado(u.getEstado());
        if (u.getFechaCreacion() != null) usuario.setFechaCreacion(u.getFechaCreacion());
        if (u.getRegion() != null) usuario.setRegion(u.getRegion());
        if (u.getComuna() != null) usuario.setComuna(u.getComuna());
        if (u.getDireccion() != null) usuario.setDireccion(u.getDireccion());

        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Long id) {
    Optional<Usuario> uOpt = usuarioService.findById(id);
    if (uOpt.isEmpty()) {
        return ResponseEntity.notFound().build(); // 404
    }

    try {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    } catch (IllegalStateException ex) {
        if ("usuario.tiene.boletas".equals(ex.getMessage())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el usuario porque tiene boletas asociadas.");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("No se puede eliminar el usuario porque tiene datos asociados.");
    } catch (Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno al eliminar el usuario.");
    }
}


}
