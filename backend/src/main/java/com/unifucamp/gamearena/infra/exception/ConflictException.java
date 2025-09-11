package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends GameArenaException {
    public ConflictException(String detail) {
        super(HttpStatus.CONFLICT, "Conflito de dados", detail);
    }
}
