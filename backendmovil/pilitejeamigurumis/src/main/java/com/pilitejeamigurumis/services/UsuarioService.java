package com.pilitejeamigurumis.services;

import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findByAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    Optional<Usuario> delete(Usuario usuario);

    Usuario login(String email, String password);

    void deleteById(Long id);

}
