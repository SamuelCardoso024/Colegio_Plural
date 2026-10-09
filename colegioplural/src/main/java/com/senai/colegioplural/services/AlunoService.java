package com.senai.colegioplural.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.colegioplural.models.Aluno;
import com.senai.colegioplural.repositories.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ResponsavelService responsavelService;

    public Aluno cadastrar(Aluno aluno) {
        aluno.setResponsavel(responsavelService.buscar(aluno.getResponsavel().getId()));
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Aluno aluno, Integer id) {
    Aluno alunoExistente = buscar(id);
    if (alunoExistente == null) {
        throw new RuntimeException("Aluno não encontrado");
    }

    if (aluno.getNome() != null) {
        alunoExistente.setNome(aluno.getNome());
    }

    if (aluno.getResponsavel() != null && aluno.getResponsavel().getId() != null) {
        alunoExistente.setResponsavel(responsavelService.buscar(aluno.getResponsavel().getId()));
    }

    return alunoRepository.save(alunoExistente);
    }

    public void deletar(Integer id) {
        alunoRepository.deleteById(id);
    }

    public Aluno buscar(Integer id) {
        return alunoRepository.findById(id).get();
    }

    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }
}