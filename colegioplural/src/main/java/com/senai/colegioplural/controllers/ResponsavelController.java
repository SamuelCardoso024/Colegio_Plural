package com.senai.colegioplural.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senai.colegioplural.models.Responsavel;
import com.senai.colegioplural.services.ResponsavelService;

@RestController
@RequestMapping("/responsavel")
public class ResponsavelController {
    @Autowired
    private ResponsavelService responsavelService;

    @GetMapping("/listar")
    public List<Responsavel> listarResponsaveis(@RequestParam(required = true) String termo) {
        return responsavelService.listarResponsaveis(termo);
    }

    @GetMapping("/buscar/{id}")
    public Responsavel buscar(@PathVariable Integer id) {
        return responsavelService.buscar(id);
    }

    @PostMapping("/salvar")
    public Responsavel salvar(@RequestBody Responsavel responsavel) {
        return responsavelService.salvar(responsavel);
    }

    @PutMapping("/atualizar/{id}")
    public Responsavel atualizar(@RequestBody Responsavel responsavel,
            @PathVariable Integer id) {
        return responsavelService.atualizar(responsavel, id);
    }

    @DeleteMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id) {
        responsavelService.deletar(id);
        return "Responsável deletado com sucesso";
    }
}
