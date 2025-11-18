package br.com.unit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void enviarEmail(String para, String assunto, String texto) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(texto);
        try {
            mailSender.send(mensagem);
            log.info("Email enviado para {}", para);
        } catch (MailException e) {
            log.error("Falha ao enviar email para {}: {}", para, e.getMessage(), e);
            throw new RuntimeException("Erro ao enviar email", e);
        }
    }
}
