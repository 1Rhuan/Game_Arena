package com.unifucamp.gamearena.participante.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ParticipanteResponseDTO(
        Long id,
        String nomeCompleto,
        String apelido,
        String email,
        String statusPagamento,
        LocalDate dataNascimento,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,
        boolean ativo
) {

}
