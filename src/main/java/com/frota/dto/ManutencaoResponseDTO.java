package com.frota.dto;

import com.frota.model.enums.StatusManutencao;
import java.time.LocalDateTime;

public record ManutencaoResponseDTO(
        Long id,
        String placaVeiculo,
        String modeloVeiculo,
        String descricaoProblema,
        StatusManutencao status,
        LocalDateTime dataAbertura,
        LocalDateTime dataFinalizacao
) {}