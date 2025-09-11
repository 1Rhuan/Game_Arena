package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends GameArenaException {
    public NotFoundException(String detail) {
        super(HttpStatus.NOT_FOUND, "Recurso não encontrado", detail);
    }
}