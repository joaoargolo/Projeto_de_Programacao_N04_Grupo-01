package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.*;
import java.util.*;

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
    public String criarEvento(@RequestBody EventoParticipado evento) {
        eventos.add(evento);
        return "Evento cadastrado com sucesso: " + evento.getNomeEvento();
    }

    @DeleteMapping("/deletar/{id}")
    public String removerEvento(@PathVariable long id) {
        eventos.removeIf(e -> e.getIdEvento() == id);
        return "Evento com ID " + id + " removido com sucesso!";
    }
}
