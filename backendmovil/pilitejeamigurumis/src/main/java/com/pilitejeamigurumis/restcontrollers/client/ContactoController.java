package com.pilitejeamigurumis.restcontrollers.client;

import com.pilitejeamigurumis.entities.Contacto;
import com.pilitejeamigurumis.restcontrollers.dto.contacto.ContactoRequest;
import com.pilitejeamigurumis.services.ContactoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "*")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping
    public ResponseEntity<?> enviar(@RequestBody ContactoRequest dto) {

        Contacto c = new Contacto();
        c.setRun(dto.getRun());
        c.setNombre(dto.getNombre());
        c.setApellidos(dto.getApellidos());
        c.setEmail(dto.getEmail());
        c.setComentario(dto.getComentario());

        contactoService.guardar(c);

        return ResponseEntity.ok().build();
    }
}
