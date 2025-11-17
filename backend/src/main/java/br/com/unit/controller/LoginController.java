package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import br.com.unit.service.EspectadorService;
import java.util.*;
import br.com.unit.dto.LoginDTO;
import br.com.unit.service.GerenteService;
import br.com.unit.service.StaffService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EspectadorService espectadorService;

    @Autowired
    private GerenteService gerenteService;

    @Autowired
    private StaffService staffService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

        // tenta autenticar como Espectador
        if (espectadorService.autenticar(loginDTO.email(), loginDTO.senha())) {
            return ResponseEntity.ok("Login realizado com sucesso! Tipo: ESPECTADOR");
        }

        // tenta autenticar como Gerente
        if (gerenteService.autenticar(loginDTO.email(), loginDTO.senha())) {
            return ResponseEntity.ok("Login realizado com sucesso! Tipo: GERENTE");
        }

        // tenta autenticar como Staff
        if (staffService.autenticar(loginDTO.email(), loginDTO.senha())) {
            return ResponseEntity.ok("Login realizado com sucesso! Tipo: STAFF");
        }

        return ResponseEntity.status(401).body("Email ou senha incorretos");
    }
}