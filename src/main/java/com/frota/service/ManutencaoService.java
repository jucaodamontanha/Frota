package com.frota.service;

import com.frota.dto.ManutencaoRequestDTO;
import com.frota.dto.ManutencaoResponseDTO;
import com.frota.model.Manutencao;
import com.frota.model.Veiculo;
import com.frota.model.enums.StatusManutencao;
import com.frota.repository.ManutencaoRepository;
import com.frota.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository repository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public ManutencaoResponseDTO abrir(ManutencaoRequestDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Manutencao manutencao = new Manutencao();
        manutencao.setVeiculo(veiculo);
        manutencao.setDescricaoProblema(dto.descricaoProblema());
        manutencao.setStatus(StatusManutencao.ABERTA);
        manutencao.setDataAbertura(LocalDateTime.now());

        Manutencao salva = repository.save(manutencao);
        return converterParaDTO(salva);
    }

    @Transactional
    public ManutencaoResponseDTO finalizar(Long id) {
        Manutencao m = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada"));

        m.setStatus(StatusManutencao.FINALIZADA);
        m.setDataFinalizacao(LocalDateTime.now());

        return converterParaDTO(repository.save(m));
    }

    public List<ManutencaoResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private ManutencaoResponseDTO converterParaDTO(Manutencao m) {
        return new ManutencaoResponseDTO(
                m.getId(),
                m.getVeiculo().getPlaca(),
                m.getVeiculo().getModelo(),
                m.getDescricaoProblema(),
                m.getStatus(),
                m.getDataAbertura(),
                m.getDataFinalizacao()
        );
    }
    public List<Map<String, Object>> buscarDadosMensaisGrafico() {
        List<Map<String, Object>> dadosBrutos = repository.countFinalizadasPorMes();
        String[] meses = {"", "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};

        return dadosBrutos.stream().map(dado -> {
            Map<String, Object> formatado = new HashMap<>();
            int mesNum = (int) dado.get("mes");
            formatado.put("mes", meses[mesNum]); // Transforma 1 em "Jan"
            formatado.put("total", dado.get("total"));
            return formatado;
        }).collect(Collectors.toList());
    }
}