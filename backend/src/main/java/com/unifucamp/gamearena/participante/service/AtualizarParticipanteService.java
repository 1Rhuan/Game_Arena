package com.unifucamp.gamearena.participante.service;

import com.unifucamp.gamearena.infra.email.EmailSender;
import com.unifucamp.gamearena.infra.exception.UnprocessableEntityException;
import com.unifucamp.gamearena.participante.domain.StatusPagamento;
import com.unifucamp.gamearena.participante.dto.ParticipanteResponseDTO;
import com.unifucamp.gamearena.participante.dto.ParticipanteUpdateDTO;
import com.unifucamp.gamearena.participante.mapper.ParticipanteMapper;
import com.unifucamp.gamearena.participante.repository.ParticipanteRepository;
import com.unifucamp.gamearena.shared.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AtualizarParticipanteService {

    private static final Logger log = LoggerFactory.getLogger(AtualizarParticipanteService.class);

    private final ParticipanteRepository participanteRepository;
    private final EmailSender emailSender;
    private final EmailTemplateService emailTemplateService;

    public AtualizarParticipanteService(ParticipanteRepository participanteRepository, EmailSender emailSender, EmailTemplateService emailTemplateService) {
        this.participanteRepository = participanteRepository;
        this.emailSender = emailSender;
        this.emailTemplateService = emailTemplateService;
    }

    public ParticipanteResponseDTO handle(Long id, ParticipanteUpdateDTO dto) {

        var participante = participanteRepository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException("Participante não encontrado"));

        var statusAntigo = participante.getStatusPagamento();

        participante.setNomeCompleto(dto.nomeCompleto());
        participante.setApelido(dto.apelido());
        participante.setEmail(dto.email());
        participante.setStatusPagamento(dto.statusPagamento());
        participante.setDataNascimento(dto.dataNascimento());
        participante.setAtivo(dto.ativo());
        participante.setAtualizadoEm(LocalDateTime.now());

        var atualizado = participanteRepository.save(participante);

        var statusNovo = atualizado.getStatusPagamento();

        if (statusNovo == StatusPagamento.APROVADO && statusAntigo != StatusPagamento.APROVADO) {
            emailSender.sendEmail(atualizado.getEmail(),
                    emailTemplateService.gerarMensagemAprovacao(atualizado));
        }

        if (statusNovo == StatusPagamento.REJEITADO && statusAntigo != StatusPagamento.REJEITADO) {
            emailSender.sendEmail(atualizado.getEmail(),
                    emailTemplateService.gerarMensagemRejeitado(atualizado));
        }

        if (statusNovo == StatusPagamento.PENDENTE && statusAntigo != StatusPagamento.PENDENTE) {
            emailSender.sendEmail(atualizado.getEmail(),
                    emailTemplateService.gerarMensagemPendente(atualizado));
        }

        return ParticipanteMapper.toResponseDTO(atualizado);
    }
}
