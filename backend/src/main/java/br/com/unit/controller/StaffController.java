package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.unit.classes.Staff;
import br.com.unit.classes.Evento;
import br.com.unit.dto.StaffInputDTO;
import br.com.unit.service.StaffService;

import java.util.*;

//POST http://localhost:8080/staff
//{
//  "nome": "Luiz",
//  "cpf": "11122233344",
//  "email": "luiz@example.com",
//  "senha": "1234",
//  "dataNasc": "2005-03-12",
//  "telefone": "79995634252",
//  "perfil": "ewrfbhrwevuib",
//  "especializacao": "mixagem",
//  "eventosAuxiliados": [
//    {"idEvento": 1}
//  ]
//}

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    private List<Staff> staff = new ArrayList<>();

    @GetMapping("/listar")
    public Collection<Staff> listarStaff() { return staffService.getStaff(); }

    @PostMapping("/criar")
    public ResponseEntity<String> criarStaff(@RequestBody StaffInputDTO dto){
        try {
            Staff staff = new Staff();
            staff.setNome(dto.getNome());
            staff.setCpf(dto.getCpf());
            staff.setEmail(dto.getEmail());
            staff.setSenha(dto.getSenha());
            staff.setDataNasc(dto.getDataNasc());
            staff.setTelefone(dto.getTelefone());
            staff.setPerfil(dto.getPerfil());
            staff.setEspecializacao(dto.getEspecializacao());

            if (dto.getEventosAuxiliados() != null) {
                List<Evento> eventos = dto.getEventosAuxiliados().stream().map(id -> {
                    Evento e = new Evento();
                    e.setIdEvento(id);
                    return e;
                }).toList();
                staff.setEventosAuxiliados(new java.util.ArrayList<>(eventos));
            }

            staffService.createStaff(staff);
            return ResponseEntity.status(HttpStatus.CREATED).body("Membro do staff cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarStaff(@PathVariable int id, @RequestBody StaffInputDTO dto) {
        try {
            Staff staff = new Staff();
            staff.setNome(dto.getNome());
            staff.setCpf(dto.getCpf());
            staff.setEmail(dto.getEmail());
            staff.setSenha(dto.getSenha());
            staff.setDataNasc(dto.getDataNasc());
            staff.setTelefone(dto.getTelefone());
            staff.setPerfil(dto.getPerfil());
            staff.setEspecializacao(dto.getEspecializacao());

            if (dto.getEventosAuxiliados() != null) {
                List<Evento> eventos = dto.getEventosAuxiliados().stream().map(idEv -> {
                    Evento e = new Evento();
                    e.setIdEvento(idEv);
                    return e;
                }).toList();
                staff.setEventosAuxiliados(new java.util.ArrayList<>(eventos));
            }

            staffService.updateStaff(id, staff);
            return ResponseEntity.ok("Membro do staff com o id:" + id + " atualizado com sucesso!!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerStaff(@PathVariable int id){
        try {
            staffService.deleteStaff(id);
            return ResponseEntity.ok("Membro do staff com ID " + id + " removido com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/buscar/email")
    public ResponseEntity<?> buscarPorEmail(@RequestParam String email) {
        Staff staff = service.buscarPorEmail(email);
        return (staff == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(staff);
    }
}

}