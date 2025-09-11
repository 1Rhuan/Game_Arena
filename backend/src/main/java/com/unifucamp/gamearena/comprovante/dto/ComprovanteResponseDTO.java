package com.unifucamp.gamearena.comprovante.dto;

public record ComprovanteResponseDTO(
        String nomeOriginal,
        String mimeType,
        byte[] arquivo
) {
}
