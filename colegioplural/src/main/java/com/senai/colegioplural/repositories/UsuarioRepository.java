package com.senai.colegioplural.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.colegioplural.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}