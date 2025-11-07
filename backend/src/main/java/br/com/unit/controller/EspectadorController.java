package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import br.com.unit.service.EspectadorService;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//POST http://localhost:8080/espectadores
//{
//  "nome": "Ana",
//  "cpf": "11122233344",
//  "email": "ana@example.com",
//  "senha": "1234",
//  "dataNasc": "2001-07-10",
//  "telefone": "79998057227",
//  "perfil": "sokdoef"
//}

@RestController
@RequestMapping("/espectadores")
public class EspectadorController {

    @Autowired
    private EspectadorService espectadorService;

    @GetMapping("/listar")
    public Collection<Espectador> listarEspectadores() {
        return espectadorService.getEspectador();
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarEspectador(@RequestBody Espectador espectador) {
        try {
            espectadorService.createEspectador(espectador);
            return ResponseEntity.status(HttpStatus.CREATED).body("Espectador cadastrado com sucesso: " + espectador.getIdEspectador());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarEspectador(@PathVariable int id, @RequestBody Espectador espectador) {
        try {
            espectadorService.updateEspectador(id, espectador);
            return ResponseEntity.ok("Espectador com o id:" + id + "atualizado com sucesso!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerEspectador(@PathVariable int id) {
        try {
            espectadorService.deleteEspectador(id);
            return ResponseEntity.ok("Espectador com ID " + id + " removido com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}