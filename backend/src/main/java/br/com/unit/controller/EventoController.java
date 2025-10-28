package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.*;
import java.util.*;

import br.com.unit.service.EventoService;

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

    @Autowired
    private EventoService eventoService;

    private List<EventoParticipado> eventos = new ArrayList<>();

    @GetMapping("/listar")
    public Collection<Evento> listarEventos() {
        return eventoService.getEvento();
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarEvento(@RequestBody EventoParticipado evento) {
        eventos.add(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body("Evento cadastrado com sucesso: " + evento.getNomeEvento());
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarEvento(@PathVariable int id, @RequestBody Evento evento) {

        eventoService.updateEvento(id, evento);
        return ResponseEntity.ok("Evento com o id:" + id + "atualizado com sucesso!!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerEvento(@PathVariable int id) {
        eventoService.deleteEvento(id);;
        return ResponseEntity.ok("Evento com ID " + id + " removido com sucesso!");
    }
}
