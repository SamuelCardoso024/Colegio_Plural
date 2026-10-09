package com.senai.colegioplural.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.senai.colegioplural.dtos.AtendimentoResponse;
import com.senai.colegioplural.models.Atendimento;
import com.senai.colegioplural.services.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping("/listar")

    public List<AtendimentoResponse> listarAtendimentos() {
        return atendimentoService.listarAtendimentos();
    }

    @GetMapping("/buscar/{id}")
    public AtendimentoResponse buscarAtendimento(@PathVariable Integer id) {
        return atendimentoService.buscar(id);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarAtendimento(@PathVariable Integer id) {
        atendimentoService.deletar(id);
        return "Atendimento deletado com sucesso";
    }

    @PostMapping("/cadastrar")
    public String cadastrarAtendimento(@RequestBody Atendimento atendimento) {
        atendimentoService.cadastrar(atendimento);
        return "Atendimento cadastrado com sucesso";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarAtendimento(@RequestBody Atendimento atendimento,
            @PathVariable Integer id) {
        atendimentoService.atualizar(atendimento, id);
        return "Atendimento atualizado com sucesso";
    }
}
