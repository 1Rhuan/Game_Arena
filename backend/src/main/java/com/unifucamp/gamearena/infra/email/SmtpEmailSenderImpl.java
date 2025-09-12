package com.unifucamp.gamearena.infra.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailSenderImpl implements EmailSender {
    private static final Logger log = LoggerFactory.getLogger(SmtpEmailSenderImpl.class);
    private final JavaMailSender mailSender;

    public SmtpEmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${app.email.noreply:noreply@teste.com}")
    private String noreplyEmail;

    @Override
    @Async
    public void sendEmail(String destinatario, String texto) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(destinatario);
            helper.setFrom(noreplyEmail);
            helper.setSubject("Atualização da sua inscrição - GameArena");
            helper.setText(texto, true);

            mailSender.send(message);
            log.info("Email enviado com sucesso! E-mail: {}", destinatario);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail de confirmação de pagamento", e);
        }
    }
}
