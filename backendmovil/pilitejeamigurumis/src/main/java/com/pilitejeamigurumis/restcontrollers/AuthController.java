package com.pilitejeamigurumis.restcontrollers;

import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.repositories.UsuarioRepository;
import com.pilitejeamigurumis.restcontrollers.dto.auth.LoginRequest;
import com.pilitejeamigurumis.restcontrollers.dto.auth.RegistroRequest;
import com.pilitejeamigurumis.security.JwtTokenProvider;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UsuarioRepository usuarioRepo,
                          PasswordEncoder passwordEncoder,
                          JwtTokenProvider jwtTokenProvider) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroRequest req) {
        if (usuarioRepo.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email ya registrado"));
        }

        Usuario u = new Usuario();
        u.setNombre(req.getNombre());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
String rol = req.getRol() != null ? req.getRol().toUpperCase() : "CLIENTE";
u.setRol(rol);
        u.setEstado("ACTIVO");
        u.setRegion(req.getRegion());
        u.setComuna(req.getComuna());
        u.setDireccion(req.getDireccion());

        usuarioRepo.save(u);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {

        Usuario u = usuarioRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Credenciales inválidas")
                );
            if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) {
                throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
            }

        String token = jwtTokenProvider.generateToken(u);

        Map<String, Object> payload = new HashMap<>();
        payload.put("accessToken", token);
        payload.put("id", u.getId());
        payload.put("nombre", u.getNombre());
        payload.put("email", u.getEmail());
        payload.put("role", u.getRol());

        return ResponseEntity.ok(payload);
    }
}
