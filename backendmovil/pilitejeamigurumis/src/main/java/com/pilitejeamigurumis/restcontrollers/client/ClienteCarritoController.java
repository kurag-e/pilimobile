package com.pilitejeamigurumis.restcontrollers.client;

import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.repositories.UsuarioRepository;
import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaDto;
import com.pilitejeamigurumis.restcontrollers.dto.boleta.BoletaResumenDto;
import com.pilitejeamigurumis.restcontrollers.dto.carrito.AddItemRequest;
import com.pilitejeamigurumis.restcontrollers.dto.carrito.CarritoDto;
import com.pilitejeamigurumis.restcontrollers.dto.carrito.CheckoutRequest;
import com.pilitejeamigurumis.services.CarritoService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClienteCarritoController {

    private final CarritoService carritoService;
    private final UsuarioRepository usuarioRepo;

    public ClienteCarritoController(CarritoService carritoService,
                                    UsuarioRepository usuarioRepo) {
        this.carritoService = carritoService;
        this.usuarioRepo = usuarioRepo;
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
        if (principal instanceof UserDetails userDetails) {
            email = userDetails.getUsername();
        } else if (principal instanceof Usuario usuario) {
            return usuario;
        } else {
            email = auth.getName();
        }

        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró usuario con email: " + email
                ));
    }

    @GetMapping("/carrito")
    public CarritoDto getCarrito() {
        Usuario u = usuarioActual();
        return carritoService.getCarritoDtoForUser(u);
    }

    @PostMapping("/carrito/items")
    public CarritoDto addItem(@RequestBody AddItemRequest req) {
        Usuario u = usuarioActual();
        return carritoService.addItem(u, req.getProductoId(), req.getCantidad());
    }

    @PostMapping("/carrito/checkout")
    public BoletaDto checkout(@RequestBody CheckoutRequest req) {
        Usuario u = usuarioActual();
        return carritoService.checkout(u, req);
    }

    @GetMapping("/historial")
    public List<BoletaResumenDto> historial() {
        Usuario u = usuarioActual();
        return carritoService.getHistorial(u);
    }
}
