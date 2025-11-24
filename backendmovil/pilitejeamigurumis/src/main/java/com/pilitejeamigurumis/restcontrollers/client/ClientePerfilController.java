package com.pilitejeamigurumis.restcontrollers.client;

import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.repositories.UsuarioRepository;
import com.pilitejeamigurumis.restcontrollers.dto.perfil.PerfilDto;
import com.pilitejeamigurumis.restcontrollers.dto.perfil.PasswordChangeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClientePerfilController {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public ClientePerfilController(UsuarioRepository usuarioRepo,
                                   PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    private Usuario usuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()
                || auth.getPrincipal() == null
                || "anonymousUser".equals(auth.getPrincipal())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "No hay usuario autenticado (token ausente o inválido)"
            );
        }

        String email;
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails ud) {
            email = ud.getUsername();
        } else if (principal instanceof Usuario u) {
            return u;
        } else {
            email = auth.getName();
        }

        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró usuario con email: " + email
                ));
    }

    @GetMapping("/perfil")
    public PerfilDto getPerfil() {
        Usuario u = usuarioActual();
        return PerfilDto.fromEntity(u);
    }

    @PutMapping("/perfil")
    public PerfilDto actualizarPerfil(@RequestBody PerfilDto req) {
        Usuario u = usuarioActual();

        if (req.getEmail() != null && !req.getEmail().equalsIgnoreCase(u.getEmail())) {
            boolean exists = usuarioRepo.existsByEmail(req.getEmail());
            if (exists) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "El correo ya está en uso por otro usuario"
                );
            }
        }

        req.mergeInto(u);
        usuarioRepo.save(u);

        return PerfilDto.fromEntity(u);
    }

    @PutMapping("/perfil/password")
    public void cambiarPassword(@RequestBody PasswordChangeRequest req) {
        Usuario u = usuarioActual();

        if (req.getActualPassword() == null || req.getNuevaPassword() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Debe indicar contraseña actual y nueva contraseña"
            );
        }

        if (!passwordEncoder.matches(req.getActualPassword(), u.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La contraseña actual no es correcta"
            );
        }

        if (req.getNuevaPassword().length() < 6) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La nueva contraseña debe tener al menos 6 caracteres"
            );
        }

        u.setPassword(passwordEncoder.encode(req.getNuevaPassword()));
        usuarioRepo.save(u);
    }
}
