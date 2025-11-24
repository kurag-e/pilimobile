package com.pilitejeamigurumis.services;

import com.pilitejeamigurumis.entities.Contacto;
import com.pilitejeamigurumis.repositories.ContactoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactoServiceImpl implements ContactoService {

    private final ContactoRepository contactoRepo;

    public ContactoServiceImpl(ContactoRepository contactoRepo) {
        this.contactoRepo = contactoRepo;
    }

    @Override
    public Contacto guardar(Contacto contacto) {
        contacto.setId(null);
        contacto.setFechaEnvio(LocalDateTime.now());
        return contactoRepo.save(contacto);
    }
}
