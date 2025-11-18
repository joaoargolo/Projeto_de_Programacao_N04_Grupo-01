package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import br.com.unit.classes.Condutor;
import br.com.unit.classes.Gerente;
import br.com.unit.classes.Staff;
import br.com.unit.service.EspectadorService;
import br.com.unit.service.CondutorService;
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

    @Autowired
    private CondutorService condutorService;

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
            Gerente g = gerenteService.getByEmail(loginDTO.email());
            PaginaUsuarioDTO dto = new PaginaUsuarioDTO();
            dto.setNome(g.getNome());
            dto.setCpf(g.getCpf());
            dto.setFuncao("GERENTE");
            dto.setDataNascimento(g.getDataNasc());
            return ResponseEntity.ok(dto);
        }

        if (staffService.autenticar(loginDTO.email(), loginDTO.senha())) {
            Staff s = staffService.getByEmail(loginDTO.email());
            PaginaUsuarioDTO dto = new PaginaUsuarioDTO();
            dto.setNome(s.getNome());
            dto.setCpf(s.getCpf());
            dto.setFuncao("STAFF");
            dto.setDataNascimento(s.getDataNasc());
            return ResponseEntity.ok(dto);
        }

        if (condutorService.autenticar(loginDTO.email(), loginDTO.senha())) {
            Condutor c = condutorService.getByEmail(loginDTO.email());
            PaginaUsuarioDTO dto = new PaginaUsuarioDTO();
            dto.setNome(c.getNome());
            dto.setCpf(c.getCpf());
            dto.setFuncao("CONDUTOR");
            dto.setDataNascimento(c.getDataNasc());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(401).body("Email ou senha incorretos");
    }
}