package br.com.unit.controller;

import br.com.unit.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-email")
@RequiredArgsConstructor
public class TestEmailController {

    private final EmailService emailService;

    @GetMapping
    public String enviar() {
        emailService.enviarEmail(
                "SEU_EMAIL@gmail.com",   // o e-mail que vai receber
                "TESTE",
                "Funcionou!"
        );
        return "Enviado!";
    }
}
