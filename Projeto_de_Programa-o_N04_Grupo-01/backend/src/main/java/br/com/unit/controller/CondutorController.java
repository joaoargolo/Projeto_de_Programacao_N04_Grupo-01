package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Condutor;
import br.com.unit.service.CondutorService;
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

    @Autowired
    private CondutorService condutorService;

    int id = 0;

    private List<Condutor> condutores = new ArrayList<>();

    @GetMapping("/listar")
    public Collection<Condutor> listarCondutores() {
        return condutorService.getCondutor();
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarCondutor(@RequestBody Condutor condutor) {
        try {
            condutorService.createCondutor(condutor);
            id++;
            condutorService.setId(id, condutor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Condutor cadastrado com sucesso: " + condutor.getIdCondutor());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarCondutor(@PathVariable int id, @RequestBody Condutor condutor) {

        condutorService.updateCondutor(id, condutor);
        return ResponseEntity.ok("Condutor com o id:" + id + "atualizado com sucesso!!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerCondutor(@PathVariable int id) {
        condutorService.deleteCondutor(id);
        return ResponseEntity.ok("Condutor com ID " + id + " removido com sucesso!");
    }
}
