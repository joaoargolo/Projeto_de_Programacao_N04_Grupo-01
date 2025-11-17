package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import br.com.unit.service.EspectadorService;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Espectador cadastrado com sucesso: " + espectador.getIdEspectador());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarEspectador(@PathVariable int id, @RequestBody Espectador espectador) {
        try {
            espectadorService.updateEspectador(id, espectador);
            return ResponseEntity.ok("Espectador com o id:" + id + " atualizado com sucesso!!");
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


    @GetMapping("/buscar/email")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        try {
            Espectador espectador = espectadorService.buscarPorEmail(email);
            return ResponseEntity.ok(espectador);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
