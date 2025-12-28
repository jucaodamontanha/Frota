package com.frota.service;

import com.frota.model.Manutencao;
import com.frota.model.enums.StatusManutencao;
import com.frota.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository repository;

    // Abrir nova manutenção
    public Manutencao abrir(Manutencao manutencao) {
        manutencao.setStatus(StatusManutencao.ABERTA);
        manutencao.setDataAbertura(LocalDateTime.now());
        return repository.save(manutencao);
    }

    // Listar todas para o Gestor
    public List<Manutencao> listarTodas() {
        return repository.findAll();
    }
}