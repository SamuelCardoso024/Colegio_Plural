package com.senai.colegioplural.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.senai.colegioplural.models.Responsavel;
import com.senai.colegioplural.repositories.ResponsavelRepository;

@Service
public class ResponsavelService {
    @Autowired
    private ResponsavelRepository responsavelRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Responsavel> listarResponsaveis(String termo) {
        return responsavelRepository.listarResponsaveis(termo);
    }

    public Responsavel buscar(Integer id) {
        return responsavelRepository.findById(id).get();
    }

    public Responsavel salvar(Responsavel responsavel) {
        responsavel.setCpf(passwordEncoder.encode(responsavel.getCpf()));
        return responsavelRepository.save(responsavel);

    }

    public Responsavel atualizar(Responsavel responsavel, Integer id) {
        Responsavel responsavelExistente = buscar(id);
        if (responsavelExistente == null) throw new RuntimeException("Responsávelnão encontrado");
        if (responsavel.getCpf() != null)
        responsavelExistente.setCpf(passwordEncoder.encode(responsavel.getCpf()));
        if (responsavel.getNome() != null)
        responsavelExistente.setNome(responsavel.getNome());
        return responsavelRepository.save(responsavelExistente);
    }

    public void deletar(Integer id) {
        responsavelRepository.deleteById(id);
    }
}