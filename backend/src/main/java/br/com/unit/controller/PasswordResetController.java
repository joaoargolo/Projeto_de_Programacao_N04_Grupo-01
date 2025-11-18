package br.com.unit.controller;

import br.com.unit.classes.*;
import br.com.unit.repository.*;
import br.com.unit.service.EmailService;
import br.com.unit.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;
    private final EmailService emailService;

    private final CondutorRepository condutorRepo;
    private final EspectadorRepository espectadorRepo;
    private final GerenteRepository gerenteRepo;
    private final StaffRepository staffRepo;

    @PostMapping("/redefinir-senha")
    public String redefinirSenha(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String tipo = request.get("tipo");

        Object usuario = switch (tipo) {
            case "CONDUTOR" -> condutorRepo.findByEmail(email).orElse(null);
            case "ESPECTADOR" -> espectadorRepo.findByEmail(email).orElse(null);
            case "GERENTE" -> gerenteRepo.findByEmail(email).orElse(null);
            case "STAFF" -> staffRepo.findByEmail(email).orElse(null);
            default -> null;
        };

        if (usuario == null) {
            return "Email não encontrado para o tipo informado.";
        }

        Integer userId = switch (tipo) {
            case "CONDUTOR" -> ((Condutor) usuario).getIdCondutor();
            case "ESPECTADOR" -> ((Espectador) usuario).getIdEspectador();
            case "GERENTE" -> ((Gerente) usuario).getIdGerente();
            case "STAFF" -> ((Staff) usuario).getIdStaff();
            default -> null;
        };

        if (userId == null) {
            return "Erro ao identificar usuário.";
        }

        String token = passwordResetService.gerarToken(userId, tipo);

        String link = "http://localhost:8080/password/reset?token=" + token;

        return "Token gerado com sucesso! Link: " + link +
                " - Use este link para redefinir sua senha.";
    }

    @PostMapping("/reset")
    public String resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String novaSenha = request.get("novaSenha");

        passwordResetService.resetarSenha(token, novaSenha);
        return "Senha redefinida com sucesso!";
    }
}