package com.senai.colegioplural.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.senai.colegioplural.dtos.AtendimentoResponse;
import com.senai.colegioplural.models.Atendimento;
import com.senai.colegioplural.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private UsuarioService usuarioService;

    public List<AtendimentoResponse> listarAtendimentos() {
        return atendimentoRepository.listarAtendimentos().stream()
                .map(atendimento -> new AtendimentoResponse(atendimento.getData(), atendimento.getAluno()))
                .toList();
    }

    public AtendimentoResponse buscar(Integer id) {
        Atendimento atendimento = atendimentoRepository.findById(id).get();
        return new AtendimentoResponse(atendimento.getData(),
                atendimento.getAluno());
    }

    public void deletar(Integer id) {
        atendimentoRepository.deleteById(id);
    }

    public Atendimento cadastrar(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public Atendimento atualizar(Atendimento atendimento, Integer id) {
        Atendimento atendimentoExistente =
        atendimentoRepository.findById(id).get();
        if (atendimentoExistente == null) throw new RuntimeException("Atendimento não encontrado");
        if (atendimento.getData() != null)
        atendimentoExistente.setData(atendimento.getData());
        if (atendimento.getAluno() != null)
        atendimentoExistente.setAluno(alunoService.buscar(atendimento.getAluno().getId()))
        ;
        if (atendimento.getUsuario() != null)
        atendimentoExistente.setUsuario(usuarioService.buscar(atendimento.getUsuario().getId()));
        return atendimentoRepository.save(atendimentoExistente);
}
}
