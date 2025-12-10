package com.kotlin.usuario1.service;
import com.kotlin.usuario1.model.Usuario;
import com.kotlin.usuario1.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public Usuario registrar(Usuario usuario) {
        return repo.save(usuario);
    }

    public Usuario login(String email, String password) {
        return repo.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}