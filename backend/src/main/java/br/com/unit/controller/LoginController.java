package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import br.com.unit.service.EspectadorService;
import java.util.*;
import br.com.unit.dto.LoginDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/login")
public class LoginController {

    private EspectadorService espectadorService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

        boolean valido = espectadorService.autenticar(loginDTO.email(), loginDTO.senha());

        if (valido) {
            return ResponseEntity.ok("Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Email ou senha incorretos");
        }
    }
}