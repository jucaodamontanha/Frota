package com.frota.dto;

import java.math.BigDecimal;

public record OrcamentoRequestDTO(
        Long manutencaoId,
        Long oficinaId,
        BigDecimal valorTotal,
        String observacoes
) {}