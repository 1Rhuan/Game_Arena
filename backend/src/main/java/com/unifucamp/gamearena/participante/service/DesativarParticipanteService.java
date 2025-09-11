package com.unifucamp.gamearena.participante.service;

import com.unifucamp.gamearena.infra.exception.NotFoundException;
import com.unifucamp.gamearena.participante.domain.Participante;
import com.unifucamp.gamearena.participante.repository.ParticipanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DesativarParticipanteService {

    private static final Logger log = LoggerFactory.getLogger(DesativarParticipanteService.class);
    private final ParticipanteRepository participanteRepository;

    public DesativarParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public void executar(Long participanteId){
        log.info("Desativando inscrição com ID: {}", participanteId);

        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new NotFoundException("Participante com id=" + participanteId + " não encontrado"));

        participante.setAtivo(false);
        participanteRepository.save(participante);
        log.info("Participante id={} desabilitado com sucesso", participanteId);
    }
}
