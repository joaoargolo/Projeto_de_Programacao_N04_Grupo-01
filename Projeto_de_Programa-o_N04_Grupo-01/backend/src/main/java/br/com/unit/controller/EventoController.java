package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.*;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//POST http://localhost:8080/eventos
//{
//  "nomeEvento": "Workshop Java",
//  "descricaoEvento": "Aprenda Spring Boot",
//  "dataInicio": "2025-10-20",
//  "dataFim": "2025-10-21",
//  "capacidade": 50
//}

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private List<EventoParticipado> eventos = new ArrayList<>();

    @GetMapping("/listar")
    public List<EventoParticipado> listarEventos() {
        return eventos;
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarEvento(@RequestBody EventoParticipado evento) {
        eventos.add(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Evento cadastrado com sucesso: " + evento.getNomeEvento());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerEvento(@PathVariable int id) {
        eventos.removeIf(e -> e.getIdEvento() == id);
        return ResponseEntity.ok("Evento com ID " + id + " removido com sucesso!");
    }
}
