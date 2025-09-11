package com.unifucamp.gamearena.participante.dto;

public record PaticipanteShortResponseDTO(
        Long id,
        String nomeCompleto,
        String nickname,
        String email,
        boolean pagamento,
        String comprovantePagamento,
        boolean ativo
) {
}
