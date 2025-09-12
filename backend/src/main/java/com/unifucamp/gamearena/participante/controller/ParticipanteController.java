package com.unifucamp.gamearena.participante.controller;


import com.unifucamp.gamearena.comprovante.dto.ComprovanteResponseDTO;
import com.unifucamp.gamearena.comprovante.service.DownloadComprovanteService;
import com.unifucamp.gamearena.participante.dto.ParticipanteRequestDTO;
import com.unifucamp.gamearena.participante.dto.ParticipanteResponseDTO;
import com.unifucamp.gamearena.participante.dto.ParticipanteUpdateDTO;
import com.unifucamp.gamearena.participante.service.AtualizarParticipanteService;
import com.unifucamp.gamearena.participante.service.BuscarParticipanteService;
import com.unifucamp.gamearena.participante.service.CadastrarParticipanteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    private final CadastrarParticipanteService cadastrarParticipanteService;
    private final BuscarParticipanteService buscarParticipanteService;
    private final DownloadComprovanteService  downloadComprovanteService;
    private final AtualizarParticipanteService atualizarParticipanteService;

    public ParticipanteController(CadastrarParticipanteService cadastrarParticipanteService, BuscarParticipanteService buscarParticipanteService, DownloadComprovanteService downloadComprovanteService, AtualizarParticipanteService atualizarParticipanteService) {
        this.cadastrarParticipanteService = cadastrarParticipanteService;
        this.buscarParticipanteService = buscarParticipanteService;
        this.downloadComprovanteService = downloadComprovanteService;
        this.atualizarParticipanteService = atualizarParticipanteService;
    }

    @PostMapping
    public ResponseEntity<Void> createParticipante(
            @RequestPart("participante") ParticipanteRequestDTO participante,
            @RequestPart(value = "comprovante") MultipartFile comprovante){

        cadastrarParticipanteService.handle(participante, comprovante);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ParticipanteResponseDTO>> listar(
            @RequestParam(required = false) String status,
            Pageable pageable) {
        return ResponseEntity.ok(buscarParticipanteService.listar(status, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteResponseDTO> buscarParticipante(@PathVariable Long id) {
        return ResponseEntity.ok(buscarParticipanteService.porId(id));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadComprovante(@PathVariable Long id){

        ComprovanteResponseDTO comprovante = downloadComprovanteService.handle(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(comprovante.mimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + comprovante.nomeOriginal() + "\"")
                .body(comprovante.arquivo());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ParticipanteUpdateDTO dto
    ) {
        return ResponseEntity.ok(atualizarParticipanteService.handle(id, dto));
    }
}
