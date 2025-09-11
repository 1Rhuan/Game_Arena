package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;

public class FileStorageException extends GameArenaException {
    public FileStorageException(String detail) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar arquivo", detail);
    }
}