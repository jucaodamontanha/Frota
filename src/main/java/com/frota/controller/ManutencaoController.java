package com.frota.controller;

import com.frota.dto.ManutencaoRequestDTO;
import com.frota.model.Manutencao;
import com.frota.model.Veiculo;
import com.frota.model.enums.StatusManutencao;
import com.frota.repository.ManutencaoRepository;
import com.frota.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/manutencoes")
@CrossOrigin(origins = "http://localhost:5173")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository repository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/abertas")
    public List<Manutencao> listarAbertas() {
        return repository.findByStatus(StatusManutencao.ABERTA);
    }

    @PostMapping
    public ResponseEntity<Manutencao> criar(@RequestBody ManutencaoRequestDTO dados) {
        // 1. Busca o veículo para garantir que ele existe
        Veiculo veiculo = veiculoRepository.findById(dados.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        // 2. Cria a manutenção manualmente
        Manutencao nova = new Manutencao();
        nova.setVeiculo(veiculo);
        nova.setDescricaoProblema(dados.descricaoProblema());
        nova.setDataAbertura(LocalDateTime.now());
        nova.setStatus(StatusManutencao.ABERTA);

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(nova));
    }
}