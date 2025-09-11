package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;

public class FileValidationException extends GameArenaException {
    public FileValidationException(String detail) {
        super(HttpStatus.BAD_REQUEST, "Arquivo inválido", detail);
    }
}