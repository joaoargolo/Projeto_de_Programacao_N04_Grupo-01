package br.com.unit.controller;

import br.com.unit.classes.Espectador;
import br.com.unit.dto.LoginRequest;
import br.com.unit.repository.EspectadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.unit.service.PasswordService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EspectadorRepository espectadorRepository;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Espectador> espectadorOpt = espectadorRepository.findByEmail(loginRequest.getEmail());

        if (espectadorOpt.isEmpty()) {
            return ResponseEntity.status(401).body(Map.of("erro", "Email ou senha incorretos"));
        }

        Espectador espectador = espectadorOpt.get();
        boolean senhaCorreta = passwordService.verificar(loginRequest.getSenha(), espectador.getSenha());

        if (!senhaCorreta) {
            return ResponseEntity.status(401).body(Map.of("erro", "Email ou senha incorretos"));
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Login realizado com sucesso");
        resposta.put("usuario", Map.of(
            "id", espectador.getIdEspectador(),
            "nome", espectador.getNome(),
            "email", espectador.getEmail()
        ));

        return ResponseEntity.ok(resposta);
    }
}
