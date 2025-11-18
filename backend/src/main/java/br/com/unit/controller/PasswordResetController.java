package br.com.unit.controller;

import br.com.unit.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/senha")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService service;

    @PostMapping("/solicitar")
    public String solicitarToken(
            @RequestParam Integer id,
            @RequestParam String tipo
    ) {
        return service.gerarToken(id, tipo.toUpperCase());
    }

    @GetMapping("/validar")
    public Object validar(@RequestParam String token) {
        return service.validarToken(token);
    }

    @PostMapping("/resetar")
    public String resetarSenha(
            @RequestParam String token,
            @RequestParam String novaSenha
    ) {
        service.resetarSenha(token, novaSenha);
        return "Senha alterada com sucesso!";
    }
}
