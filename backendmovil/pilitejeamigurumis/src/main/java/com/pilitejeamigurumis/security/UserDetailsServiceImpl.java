package com.pilitejeamigurumis.security;

import com.pilitejeamigurumis.entities.Usuario;
import com.pilitejeamigurumis.repositories.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepo;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        GrantedAuthority auth = new SimpleGrantedAuthority(u.getRol());

        boolean enabled = "ACTIVO".equalsIgnoreCase(u.getEstado());

        return new User(
                u.getEmail(),         
                u.getPassword(),      
                enabled,              
                true,                 
                true,                 
                true,                 
                List.of(auth)         
        );
    }
}
