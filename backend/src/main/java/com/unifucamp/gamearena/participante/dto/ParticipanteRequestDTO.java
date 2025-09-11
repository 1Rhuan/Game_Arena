package com.unifucamp.gamearena.participante.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record ParticipanteRequestDTO(
                @NotBlank(message = "O nome completo é obrigatório")
                String nomeCompleto,
                @NotBlank(message = "O nickname é obrigatório")
                String nickname,
                @Email(message = "O e-mail informado não é válido")
                @NotBlank(message = "O e-mail é obrigatório")
                String email,
                @NotNull(message = "A data de nascimento é obrigatória")
                @Past(message = "A data de nascimento deve ser uma data no passado")
                @DateTimeFormat(pattern = "yyyy-MM-dd")
                LocalDate dataNascimento,
                @AssertTrue(message = "É obrigatório aceitar os termos")
                Boolean aceitouTermos
) {
}

