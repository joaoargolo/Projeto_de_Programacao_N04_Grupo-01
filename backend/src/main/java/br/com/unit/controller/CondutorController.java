package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Condutor;
import br.com.unit.classes.Evento;
import br.com.unit.service.CondutorService;
import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

//POST http://localhost:8080/condutores
//{
//  "nome": "Carlos Andrade",
//  "email": "carlos.andrade@empresa.com",
//  "cpf": "11223344556",
//  "senha": "senha123",
//  "dataNasc": "1988-03-14",
//  "telefone": "(81) 97777-1111",
//  "perfil": "CONDUTOR",
//  "eventosConduzidos": [
//    { "idEvento": 1 }
//  ]
//}


@RestController
@RequestMapping("/condutores")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @GetMapping("/listar")
    public Collection<Condutor> listarCondutores() {
        return condutorService.getCondutor();
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarCondutor(@RequestBody br.com.unit.dto.CondutorInputDTO dto) {
        try {
            Condutor condutor = new Condutor();
            condutor.setNome(dto.getNome());
            condutor.setCpf(dto.getCpf());
            condutor.setEmail(dto.getEmail());
            condutor.setSenha(dto.getSenha());
            condutor.setDataNasc(dto.getDataNasc());
            condutor.setTelefone(dto.getTelefone());
            condutor.setPerfil(dto.getPerfil());

            if (dto.getEventosConduzidos() != null) {
                java.util.List<Evento> eventos = dto.getEventosConduzidos().stream().map(evId -> {
                    Evento e = new Evento();
                    e.setIdEvento(evId);
                    return e;
                }).toList();
                condutor.setEventosConduzidos(new java.util.ArrayList<>(eventos));
            }

            condutorService.createCondutor(condutor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Condutor cadastrado com sucesso: " + condutor.getIdCondutor());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarCondutor(@PathVariable int id, @RequestBody br.com.unit.dto.CondutorInputDTO dto) {
        try {
            Condutor condutor = new Condutor();
            condutor.setNome(dto.getNome());
            condutor.setCpf(dto.getCpf());
            condutor.setEmail(dto.getEmail());
            condutor.setSenha(dto.getSenha());
            condutor.setDataNasc(dto.getDataNasc());
            condutor.setTelefone(dto.getTelefone());
            condutor.setPerfil(dto.getPerfil());

            if (dto.getEventosConduzidos() != null) {
                java.util.List<Evento> eventos = dto.getEventosConduzidos().stream().map(evId -> {
                    Evento e = new Evento();
                    e.setIdEvento(evId);
                    return e;
                }).toList();
                condutor.setEventosConduzidos(new java.util.ArrayList<>(eventos));
            }

            condutorService.updateCondutor(id, condutor);
            return ResponseEntity.ok("Condutor com o id: " + id + " atualizado com sucesso!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerCondutor(@PathVariable int id) {
        try {
            condutorService.deleteCondutor(id);
            return ResponseEntity.ok("Condutor com ID " + id + " removido com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {

        Optional<Condutor> opt = condutorService.buscarPorEmail(email);

        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum condutor encontrado com o email: " + email);
        }
    }
}