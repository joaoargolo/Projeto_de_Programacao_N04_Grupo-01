package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import java.util.*;

//{
//  "nome": "Ana",
//  "cpf": "11122233344",
//  "email": "ana@example.com",
//  "senha": "1234",
//  "dataNasc": "2001-07-10",
//  "idEspectador": 3
//}

@RestController
@RequestMapping("/espectadores")
public class EspectadorController {

    private List<Espectador> espectadores = new ArrayList<>();

    @GetMapping
    public List<Espectador> listarEspectadores() {
        return espectadores;
    }

    @PostMapping
    public String criarEspectador(@RequestBody Espectador espectador) {
        espectadores.add(espectador);
        return "Espectador cadastrado com sucesso: " + espectador.getIdEspectador();
    }

    @DeleteMapping("/{id}")
    public String removerEspectador(@PathVariable int id) {
        espectadores.removeIf(e -> e.getIdEspectador() == id);
        return "Espectador com ID " + id + " removido com sucesso!";
    }
}
