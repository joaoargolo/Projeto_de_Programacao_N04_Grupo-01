package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
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
//}

@RestController
@RequestMapping("/espectadores")
public class EspectadorController {

    private List<Espectador> espectadores = new ArrayList<>();

    @GetMapping("/listar")
    public List<Espectador> listarEspectadores() {
        return espectadores;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarEspectador(@RequestBody Espectador espectador) {
        espectadores.add(espectador);
        return ResponseEntity.status(HttpStatus.CREATED).body("Espectador cadastrado com sucesso: " + espectador.getIdEspectador());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerEspectador(@PathVariable int id) {
        espectadores.removeIf(e -> e.getIdEspectador() == id);
        return ResponseEntity.ok("Espectador com ID " + id + " removido com sucesso!");
    }
}
