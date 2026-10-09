package com.senai.colegioplural.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.colegioplural.models.Aluno;
import com.senai.colegioplural.services.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
        Aluno alunoCadastrado = alunoService.cadastrar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCadastrado);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@RequestBody Aluno aluno,

            @PathVariable Integer id) {
        Aluno alunoAtualizado = alunoService.atualizar(aluno, id);
        return ResponseEntity.status(HttpStatus.OK).body(alunoAtualizado);
    }

    @DeleteMapping ("/deletar/{id}")
    public ResponseEntity<String> deletarAluno(@PathVariable Integer id) {
        alunoService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Aluno> buscarAluno(@PathVariable Integer id) {
        Aluno aluno = alunoService.buscar(id);
        return ResponseEntity.status(HttpStatus.OK).body(aluno);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = alunoService.listarAlunos();
        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }
}