package com.frota.dto;

import java.time.LocalDateTime;

public record ErroRespostaDTO(
        LocalDateTime timestamp,
        int status,
        String mensagem,
        String caminho
) {}