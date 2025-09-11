package com.unifucamp.gamearena.comprovante.service;

import com.unifucamp.gamearena.comprovante.dto.ComprovanteResponseDTO;
import com.unifucamp.gamearena.infra.armazenamento.ArmazenamentoArquivos;
import com.unifucamp.gamearena.infra.exception.NotFoundException;
import com.unifucamp.gamearena.participante.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

@Service
public class DownloadComprovanteService {

    private final ParticipanteRepository participanteRepository;
    private final ArmazenamentoArquivos armazenamentoArquivos;

    public DownloadComprovanteService(ParticipanteRepository participanteRepository, ArmazenamentoArquivos armazenamentoArquivos) {
        this.participanteRepository = participanteRepository;
        this.armazenamentoArquivos = armazenamentoArquivos;
    }

    public ComprovanteResponseDTO handle(Long id){

        var participante = participanteRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Participante não encontrado")
        );

        var comprovante = participante.getComprovante();

        var arquivo = armazenamentoArquivos.recuperar(comprovante.getNomeSalvo());

        return new ComprovanteResponseDTO(
                comprovante.getNomeOriginal(),
                comprovante.getExtensao(),
                arquivo
        );
    }
}
