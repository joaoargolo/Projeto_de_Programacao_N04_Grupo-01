package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Condutor;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//POST http://localhost:8080/condutores
//{
//  "nome": "João",
//  "cpf": "12345678900",
//  "email": "joao@example.com",
//  "senha": "1234",
//  "dataNasc": "1990-05-10",
//  "eventoConduzido": "Workshop Spring Boot"
//}


@RestController
@RequestMapping("/condutores")
public class CondutorController {

    private List<Condutor> condutores = new ArrayList<>();

    @GetMapping("/listar")
    public List<Condutor> listarCondutores() {
        return condutores;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarCondutor(@RequestBody Condutor condutor) {
        condutores.add(condutor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Condutor cadastrado com sucesso: " + condutor.getIdCondutor());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerCondutor(@PathVariable int id) {
        condutores.removeIf(c -> c.getIdCondutor() == id);
        return ResponseEntity.ok("Condutor com ID " + id + " removido com sucesso!");
    }
}
