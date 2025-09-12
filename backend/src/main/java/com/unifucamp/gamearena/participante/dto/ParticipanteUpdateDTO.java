package com.unifucamp.gamearena.participante.dto;

import com.unifucamp.gamearena.participante.domain.StatusPagamento;

import java.time.LocalDate;

public record ParticipanteUpdateDTO(
        String nomeCompleto,
        String apelido,
        String email,
        LocalDate dataNascimento,
        StatusPagamento statusPagamento,
        boolean ativo
) {
}
