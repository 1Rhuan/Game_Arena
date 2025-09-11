package com.unifucamp.gamearena.infra.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailSenderImpl implements EmailSender {
    private static final Logger log = LoggerFactory.getLogger(SmtpEmailSenderImpl.class);
    private final JavaMailSender mailSender;

    public SmtpEmailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(mensagem);
            log.info("Email enviado com sucesso! E-mail: {}", to);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail de confirmação de pagamento", e);
        }
    }
}
