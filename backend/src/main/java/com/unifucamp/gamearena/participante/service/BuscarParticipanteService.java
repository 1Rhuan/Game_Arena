package com.unifucamp.gamearena.participante.service;

import com.unifucamp.gamearena.infra.exception.UnprocessableEntityException;
import com.unifucamp.gamearena.participante.domain.Participante;
import com.unifucamp.gamearena.participante.domain.StatusPagamento;
import com.unifucamp.gamearena.participante.dto.ParticipanteResponseDTO;
import com.unifucamp.gamearena.participante.mapper.ParticipanteMapper;
import com.unifucamp.gamearena.participante.repository.ParticipanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarParticipanteService {

    private static final Logger log = LoggerFactory.getLogger(BuscarParticipanteService.class);
    private final ParticipanteRepository participanteRepository;

    public BuscarParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public Page<ParticipanteResponseDTO> listar(String status, Pageable pageable) {
        Page<Participante> participantes;

        if (status != null) {
            try {
                StatusPagamento statusPagamento = StatusPagamento.valueOf(status.toUpperCase());
                participantes = participanteRepository.findByStatusPagamento(statusPagamento, pageable);
            } catch (IllegalArgumentException e) {
                throw new UnprocessableEntityException("Status inválido. Use: PENDENTE, APROVADO ou REJEITADO");
            }
        } else {
            participantes = participanteRepository.findAll(pageable);
        }

        return participantes.map(ParticipanteMapper::toResponseDTO);
    }

    public ParticipanteResponseDTO porId(Long id) {

        var participante = participanteRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException("Participante não encontrado"));

        return ParticipanteMapper.toResponseDTO(participante);
    }
}
