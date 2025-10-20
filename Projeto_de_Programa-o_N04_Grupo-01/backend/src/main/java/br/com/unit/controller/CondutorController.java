package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Condutor;

import java.util.*;

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

    @GetMapping("/condutores/listar")
    public List<Condutor> listarCondutores() {
        return condutores;
    }

    @PostMapping("/condutores/criar")
    public String criarCondutor(@RequestBody Condutor condutor) {
        condutores.add(condutor);
        return "Condutor cadastrado com sucesso: " + condutor.getIdCondutor();
    }

    @DeleteMapping("/condutores/deletar/{id}")
    public String removerCondutor(@PathVariable int id) {
        condutores.removeIf(c -> c.getIdCondutor() == id);
        return "Condutor com ID " + id + " removido com sucesso!";
    }
}
