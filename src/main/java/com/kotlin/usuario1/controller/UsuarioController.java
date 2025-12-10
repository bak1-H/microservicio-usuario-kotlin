package com.kotlin.usuario1.controller;
import com.kotlin.usuario1.model.Usuario;
import com.kotlin.usuario1.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return service.registrar(usuario);
    }


    @GetMapping("/listar")
    public Iterable<Usuario> listarUsuarios() {
        return service.listarUsuarios();
    }
    
    @GetMapping("/login")
    public ResponseEntity<Usuario> loginGET(
            @RequestParam String email,
            @RequestParam String password) {

        Usuario user = service.login(email, password);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(user);
    }
}

