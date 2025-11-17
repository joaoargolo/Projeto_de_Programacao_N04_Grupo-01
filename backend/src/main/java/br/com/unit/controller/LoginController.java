package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import br.com.unit.service.EspectadorService;
import java.util.*;
import br.com.unit.dto.LoginDTO;
import br.com.unit.dto.PaginaUsuarioDTO;
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
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {

        if (espectadorService.autenticar(loginDTO.email(), loginDTO.senha())) {
            Espectador e = espectadorService.getByEmail(loginDTO.email());
            PaginaUsuarioDTO dto = new PaginaUsuarioDTO();
            dto.setNome(e.getNome());
            dto.setCpf(e.getCpf());
            dto.setFuncao("ESPECTADOR");
            dto.setDataNascimento(e.getDataNasc());

            return ResponseEntity.ok(dto);
        }

        if (gerenteService.autenticar(loginDTO.email(), loginDTO.senha())) {
            return ResponseEntity.ok("Login realizado com sucesso! Tipo: GERENTE");
        }

        if (staffService.autenticar(loginDTO.email(), loginDTO.senha())) {
            return ResponseEntity.ok("Login realizado com sucesso! Tipo: STAFF");
        }

        return ResponseEntity.status(401).body("Email ou senha incorretos");
    }
}