package com.unifucamp.gamearena.participante.mapper;

import com.unifucamp.gamearena.participante.domain.Participante;
import com.unifucamp.gamearena.participante.dto.ParticipanteResponseDTO;

public class ParticipanteMapper {

    public static ParticipanteResponseDTO toResponseDTO(Participante participante) {
        return new ParticipanteResponseDTO(
                participante.getId(),
                participante.getNomeCompleto(),
                participante.getApelido(),
                participante.getEmail(),
                participante.getStatusPagamento(),
                participante.getDataNascimento(),
                participante.getCriadoEm(),
                participante.getAtualizadoEm(),
                participante.isAtivo()
        );
    }
}