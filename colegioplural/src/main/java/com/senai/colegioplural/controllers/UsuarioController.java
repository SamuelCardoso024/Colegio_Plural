package com.senai.colegioplural.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.senai.colegioplural.dtos.LoginRequest;
import com.senai.colegioplural.dtos.LoginResponse;
import com.senai.colegioplural.models.Usuario;
import com.senai.colegioplural.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.cadastrar(usuario);
        return "Usuário cadastrado com sucesso";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
        LoginResponse response = usuarioService.login(request);
        return ResponseEntity.ok(response);
        } catch (Exception e) {
        return ResponseEntity.status(401).body("Falha na autenticação: e-mail ou senha inválidos");
    }
    }

    @PostMapping("/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "Logout realizado com sucesso";
    }

    @PutMapping("/atualizar/{id}")
    public Usuario atualizar(@RequestBody Usuario usuario, @PathVariable Integer

    id) {
        return usuarioService.atualizar(usuario, id);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
        return "Usuário deletado com sucesso";
    }

    @GetMapping("/buscar/{id}")
    public Usuario buscar(@PathVariable Integer id) {
        return usuarioService.buscar(id);
    }

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}