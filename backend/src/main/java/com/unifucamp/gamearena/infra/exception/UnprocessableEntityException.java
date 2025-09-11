package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends GameArenaException {
    public UnprocessableEntityException(String detail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Regra de negócio violada", detail);
    }
}