package com.senai.colegioplural.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.senai.colegioplural.models.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {

    @Query(value = "SELECT at.id, at.data, at.aluno_id, at.usuario_id, al.nome AS aluno_nome, " +
            "r.nome AS responsavel_nome, r.cpf AS responsavel_cpf " +
            "FROM atendimento at " +
            "INNER JOIN aluno al ON al.id = at.aluno_id " +
            "INNER JOIN responsavel r ON r.id = al.responsavel_id " +
            "ORDER BY at.data", nativeQuery = true)
    List<Atendimento> listarAtendimentos();
}
