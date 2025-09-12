package com.unifucamp.gamearena.participante.service;

import com.unifucamp.gamearena.infra.armazenamento.ArmazenamentoArquivos;
import com.unifucamp.gamearena.infra.email.EmailSender;
import com.unifucamp.gamearena.infra.exception.FileStorageException;
import com.unifucamp.gamearena.infra.exception.UnprocessableEntityException;
import com.unifucamp.gamearena.comprovante.domain.Comprovante;
import com.unifucamp.gamearena.participante.domain.Participante;
import com.unifucamp.gamearena.participante.domain.StatusPagamento;
import com.unifucamp.gamearena.participante.dto.ParticipanteRequestDTO;
import com.unifucamp.gamearena.participante.repository.ParticipanteRepository;
import com.unifucamp.gamearena.shared.EmailTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class CadastrarParticipanteService {

    private static final Logger log = LoggerFactory.getLogger(CadastrarParticipanteService.class);
    private final ParticipanteRepository participanteRepository;
    private final ArmazenamentoArquivos armazenamentoArquivos;
    private final EmailSender emailSender;
    private final EmailTemplateService emailTemplateService;

    public CadastrarParticipanteService(ParticipanteRepository participanteRepository, ArmazenamentoArquivos armazenamentoArquivos, EmailSender emailSender, EmailTemplateService emailTemplateService) {
        this.participanteRepository = participanteRepository;
        this.armazenamentoArquivos = armazenamentoArquivos;
        this.emailSender = emailSender;
        this.emailTemplateService = emailTemplateService;
    }

    public void handle(ParticipanteRequestDTO dto, MultipartFile arquivo) {

        if (dto.aceitouTermos() == false) {
            throw new UnprocessableEntityException("O participante deve aceitar os termos");
        }

        if (dto.dataNascimento() != null &&
                Period.between(dto.dataNascimento(), LocalDate.now()).getYears() < 18) {
            throw new UnprocessableEntityException("O participante deve ter pelo menos 18 anos");
        }

        if(participanteRepository.findByEmail(dto.email()).isPresent()){
            throw new UnprocessableEntityException("Email já em uso");
        }

        try {
            var arquivoSalvo = armazenamentoArquivos.salvar(arquivo.getBytes());

            var comprovante = new Comprovante();

            comprovante.setNomeSalvo(arquivoSalvo.nome());
            comprovante.setCaminho(arquivoSalvo.caminho());
            comprovante.setNomeOriginal(arquivo.getOriginalFilename());
            comprovante.setExtensao(arquivo.getContentType());
            comprovante.setCriadoEm(LocalDateTime.now());

            var participante = new Participante();

            participante.setNomeCompleto(dto.nomeCompleto());
            participante.setApelido(dto.nickname());
            participante.setEmail(dto.email());
            participante.setDataNascimento(dto.dataNascimento());
            participante.setAceitouTermos(true);
            participante.setStatusPagamento(StatusPagamento.PENDENTE);
            participante.setAtivo(true);
            participante.setComprovante(comprovante);

            participanteRepository.save(participante);
            emailSender.sendEmail(participante.getEmail(),
                    emailTemplateService.gerarMensagemPendente(participante));

        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }
}
