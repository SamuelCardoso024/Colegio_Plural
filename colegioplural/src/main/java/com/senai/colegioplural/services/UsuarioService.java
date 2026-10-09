package com.senai.colegioplural.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.senai.colegioplural.configs.JwtService;
import com.senai.colegioplural.dtos.LoginRequest;
import com.senai.colegioplural.dtos.LoginResponse;
import com.senai.colegioplural.models.Usuario;
import com.senai.colegioplural.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest usuario) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
        Usuario usuarioRetornado = usuarioRepository.findByEmail(usuario.getEmail()).orElseThrow();
        String token = jwtService.generateToken(usuarioRetornado.getEmail(), usuarioRetornado.getPerfil());
        return new LoginResponse(usuarioRetornado.getEmail(), usuarioRetornado.getNome(), usuarioRetornado.getPerfil(), token);
    }

    public Usuario cadastrar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario, Integer id) {
    Usuario usuarioExistente = buscar(id);
        if (usuarioExistente == null) throw new RuntimeException("Usuario nãoencontrado");

        if (usuario.getNome() != null)
        usuarioExistente.setNome(usuario.getNome());
        if (usuario.getEmail() != null)
        usuarioExistente.setEmail(usuario.getEmail());
        if (usuario.getSenha() != null)
        usuarioExistente.setSenha(passwordEncoder.encode(usuario.getSenha()));
        if (usuario.getPerfil() != null)
        usuarioExistente.setPerfil(usuario.getPerfil());
        return usuarioRepository.save(usuarioExistente);
        }

    public void deletar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario buscar(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
