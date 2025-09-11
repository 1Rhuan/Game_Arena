package com.unifucamp.gamearena.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public abstract class GameArenaException extends RuntimeException {

    protected final String title;
    protected final String detail;
    protected final HttpStatus status;

    protected GameArenaException(HttpStatus status, String title, String detail) {
        super(detail);
        this.status = status;
        this.title = title;
        this.detail = detail;
    }

    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(status);
        pb.setTitle(title);
        pb.setDetail(detail);
        return pb;
    }
}