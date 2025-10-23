package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Gerente;
import java.util.*;

//POST http://localhost:8080/gerentes
//{
//  "nome": "Alan Rocha",
//  "cpf": "12345678900",
//  "email": "alan@example.com",
//  "senha": "1234",
//  "dataNasc": "2000-01-01",
//}

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    private List<Gerente> gerentes = new ArrayList<>();

    @PostMapping("/criar")
    public String cadastrarGerente(@RequestBody Gerente g) {
        gerentes.add(g);
        return "Gerente cadastrado: " + g.getNome();
    }

    @GetMapping("/listar")
    public List<Gerente> listarGerentes() {
        return gerentes;
    }

    @DeleteMapping("/deletar/{id}")
    public String removerGerente(@PathVariable long id){
        gerentes.removeIf(e -> e.getIdGerente() == id);
        return "Gerente com o id " + id + " foi removido com sucesso!";
    }
}
