package com.unifucamp.gamearena.infra.email;

public interface EmailSender {
    void sendEmail(String subject, String text);
}
