package com.frota.controller;

import com.frota.dto.DashboardDTO;
import com.frota.dto.ManutencaoRequestDTO;
import com.frota.dto.ManutencaoResponseDTO; // Você criou este no passo anterior
import com.frota.model.enums.StatusManutencao;
import com.frota.repository.ManutencaoRepository;
import com.frota.repository.VeiculoRepository;
import com.frota.service.ManutencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/manutencoes")
@CrossOrigin(origins = "http://localhost:5173")
public class ManutencaoController {

    @Autowired
    private ManutencaoService service; // Agora usamos o Service para a lógica
    @Autowired
    private ManutencaoRepository repository;
    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping
    public List<ManutencaoResponseDTO> listarTodas() {
        return service.listarTodas();
    }

    @PostMapping
    public ResponseEntity<ManutencaoResponseDTO> criar(@RequestBody ManutencaoRequestDTO dados) {
        // Toda aquela lógica de buscar veículo e setar data agora fica no Service
        return ResponseEntity.status(HttpStatus.CREATED).body(service.abrir(dados));
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<ManutencaoResponseDTO> finalizar(@PathVariable Long id) {
        return ResponseEntity.ok(service.finalizar(id));
    }
    @GetMapping("/estatisticas")
    public DashboardDTO obterEstatisticas() {
        long totalV = veiculoRepository.count();
        long abertas = repository.countByStatus(StatusManutencao.ABERTA);
        long finalizadas = repository.countByStatus(StatusManutencao.FINALIZADA);

        return new DashboardDTO(totalV, abertas, finalizadas);
    }
    @GetMapping("/grafico-mensal")
    public List<Map<String, Object>> obterDadosGrafico() {
        return service.buscarDadosMensaisGrafico();
    }
}