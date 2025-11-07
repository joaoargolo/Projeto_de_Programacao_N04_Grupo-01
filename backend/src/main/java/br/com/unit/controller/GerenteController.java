package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Gerente;
import br.com.unit.service.GerenteService;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//POST http://localhost:8080/gerentes
//{
//  "nome": "Alan Rocha",
//  "cpf": "12345678900",
//  "email": "alan@example.com",
//  "senha": "1234",
//  "dataNasc": "2000-01-01",
//  "telefone": "79995634252",
//  "perfil": "ewrfbhrwevuib"
//}

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @PostMapping("/criar")
    public ResponseEntity<String> cadastrarGerente(@RequestBody Gerente g) {
        try {
            gerenteService.createGerente(g);
            return ResponseEntity.status(HttpStatus.CREATED).body("Gerente cadastrado com sucesso: " + g.getIdGerente());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public Collection<Gerente> listarGerentes() {
        return gerenteService.getGerente();
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarGerente(@PathVariable int id, @RequestBody Gerente g) {
        try {
            gerenteService.updateGerente(id, g);
            return ResponseEntity.ok("Gerente com o id:" + id + "atualizado com sucesso!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerGerente(@PathVariable int id){
        try {
            gerenteService.deleteGerente(id);
            return ResponseEntity.ok("Gerente com o id " + id + " foi removido com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
