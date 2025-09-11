package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;

public class UnsupportedMediaTypeException extends GameArenaException {
    public UnsupportedMediaTypeException(String detail) {
        super(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Tipo de arquivo não suportado", detail);
    }
}