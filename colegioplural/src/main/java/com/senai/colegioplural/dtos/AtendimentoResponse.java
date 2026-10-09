package com.senai.colegioplural.dtos;

import java.time.LocalDate;

import com.senai.colegioplural.models.Aluno;

public class AtendimentoResponse {
    private LocalDate data;
    private Aluno aluno;

    public AtendimentoResponse() {}

    public AtendimentoResponse(LocalDate data, Aluno aluno) {
    this.data = data;
    this.aluno = aluno;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
