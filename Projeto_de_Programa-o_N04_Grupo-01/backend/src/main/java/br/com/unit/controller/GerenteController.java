package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Gerente;
import java.util.*;

//{
//  "nome": "Alan Rocha",
//  "cpf": "12345678900",
//  "email": "alan@example.com",
//  "senha": "1234",
//  "dataNasc": "2000-01-01",
//  "idGerente": 1
//}

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    private List<Gerente> gerentes = new ArrayList<>();

    @PostMapping
    public String cadastrarGerente(@RequestBody Gerente g) {
        gerentes.add(g);
        return "Gerente cadastrado: " + g.getNome();
    }

    @GetMapping
    public List<Gerente> listarGerentes() {
        return gerentes;
    }
}
