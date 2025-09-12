package com.unifucamp.gamearena.shared;

import com.unifucamp.gamearena.participante.domain.Participante;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailTemplateService {

    @Value("${app.email:teste@teste.com}")
    private String emailSuporte;

    public String gerarMensagemAprovacao(Participante p) {
        return String.format("""
            <!doctype html>
            <html lang="pt-br">
            <body style="font-family:Arial, sans-serif;background:#f5f7fb;padding:20px;">
              <div style="max-width:600px;margin:auto;background:#fff;padding:20px;border-radius:10px;border:1px solid #ddd;">
                <h2 style="color:#16a34a;">✅ Inscrição Aprovada</h2>
                <p>Olá, <b>%s</b>!</p>
                <p>Parabéns! Sua inscrição no torneio <b>GameArena</b> foi aprovada.</p>
                <p><b>Nome:</b> %s<br>
                   <b>Apelido:</b> %s<br>
                   <b>Email:</b> %s<br>
                   <b>Status:</b> APROVADO ✅</p>
                <p>Em caso de dúvidas, entre em contato conosco pelo e-mail: 
                <a href="mailto:%s">%s</a></p>
              </div>
            </body>
            </html>
            """,
                p.getApelido() != null ? p.getApelido() : p.getNomeCompleto(),
                p.getNomeCompleto(),
                p.getApelido() == null ? "-" : p.getApelido(),
                p.getEmail(),
                emailSuporte,
                emailSuporte
        );
    }

    public String gerarMensagemRejeitado(Participante p) {
        return String.format("""
            <!doctype html>
            <html lang="pt-br">
            <body style="font-family:Arial, sans-serif;background:#fef2f2;padding:20px;">
              <div style="max-width:600px;margin:auto;background:#fff;padding:20px;border-radius:10px;border:1px solid #dc2626;">
                <h2 style="color:#dc2626;">❌ Inscrição Rejeitada</h2>
                <p>Olá, <b>%s</b>.</p>
                <p>Infelizmente, sua inscrição foi <b>rejeitada</b>. 
                Recomendamos revisar seus dados e tentar novamente.</p>
                <p><b>Email:</b> %s<br>
                   <b>Status:</b> REJEITADO ❌</p>
                <p>Em caso de dúvidas, entre em contato conosco pelo e-mail: 
                <a href="mailto:%s">%s</a></p>
              </div>
            </body>
            </html>
            """,
                p.getNomeCompleto(),
                p.getEmail(),
                emailSuporte,
                emailSuporte
        );
    }

    public String gerarMensagemPendente(Participante p) {
        return String.format("""
            <!doctype html>
            <html lang="pt-br">
            <body style="font-family:Arial, sans-serif;background:#f8fafc;padding:20px;">
              <div style="max-width:600px;margin:auto;background:#fff;padding:20px;border-radius:10px;border:1px solid #9ca3af;">
                <h2 style="color:#6b7280;">⏳ Inscrição Pendente</h2>
                <p>Olá, <b>%s</b>.</p>
                <p>Sua inscrição está em análise. Em breve entraremos em contato com o resultado.</p>
                <p><b>Email:</b> %s<br>
                   <b>Status:</b> PENDENTE ⏳</p>
                <p>Em caso de dúvidas, entre em contato conosco pelo e-mail: 
                <a href="mailto:%s">%s</a></p>
              </div>
            </body>
            </html>
            """,
                p.getNomeCompleto(),
                p.getEmail(),
                emailSuporte,
                emailSuporte
        );
    }
}