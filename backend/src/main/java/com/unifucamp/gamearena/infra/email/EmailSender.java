package com.unifucamp.gamearena.infra.email;

public interface EmailSender {
    void sendEmail(String to, String subject, String text);
}
