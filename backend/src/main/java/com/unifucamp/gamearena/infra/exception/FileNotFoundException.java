package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;

public class FileNotFoundException extends GameArenaException {
    public FileNotFoundException(String detail) {
        super(HttpStatus.NOT_FOUND, "Arquivo não encontrado", detail);
    }
}
