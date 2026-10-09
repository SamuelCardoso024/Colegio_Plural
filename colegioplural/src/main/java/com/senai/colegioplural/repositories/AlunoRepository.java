package com.senai.colegioplural.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.colegioplural.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {}