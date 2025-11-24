package com.pilitejeamigurumis.services;

import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.repositories.BoletaRepository;
import com.pilitejeamigurumis.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BoletaRepository boletaRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              BoletaRepository boletaRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.boletaRepository = boletaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {

            String rawOrHashed = usuario.getPassword();
            if (!rawOrHashed.startsWith("$2a$") && !rawOrHashed.startsWith("$2b$") && !rawOrHashed.startsWith("$2y$")) {
                usuario.setPassword(passwordEncoder.encode(rawOrHashed));
            }
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> delete(Usuario usuario) {
        Optional<Usuario> u = usuarioRepository.findById(usuario.getId());
        u.ifPresent(usuarioRepository::delete);
        return u;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario login(String email, String pass) {
        return usuarioRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(pass, u.getPassword()))
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        long boletas = boletaRepository.countByUsuario(usuario);
        if (boletas > 0) {
            throw new IllegalStateException("usuario.tiene.boletas");
        }

        usuarioRepository.delete(usuario);
    }
}
