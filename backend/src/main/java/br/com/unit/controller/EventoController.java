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
//  "nomeEvento": "Workshop de Inovação Atualizado",
//  "descricaoEvento": "Evento revisado com novas palestras",
//  "dataInicio": "2025-11-12",
//  "dataFim": "2025-11-13",
//  "capacidade": 250,
//  "gerente": { "idGerente": 1 },
//  "staffs": [
//    { "idStaff": 2 },
//    { "idStaff": 3 }
//  ],
//  "condutores": [
//    { "idCondutor": 1 }
//  ]
//}


@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/listar")
    public Collection<Evento> listarEventos() {
        return eventoService.getEvento();
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarEvento(@RequestBody Evento evento) {
        try {
            eventoService.createEvento(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body("Evento cadastrado com sucesso: " + evento.getIdEvento());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarEvento(@PathVariable int id, @RequestBody Evento evento) {
        try {
            eventoService.updateEvento(id, evento);
            return ResponseEntity.ok("Evento com o id:" + id + "atualizado com sucesso!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerEvento(@PathVariable int id) {
        try {
            eventoService.deleteEvento(id);;
            return ResponseEntity.ok("Evento com ID " + id + " removido com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}