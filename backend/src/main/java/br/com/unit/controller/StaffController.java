package br.com.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.unit.classes.Staff;
import br.com.unit.service.StaffService;

import java.util.*;

//POST http://localhost:8080/staff
//{
//  "nome": "Luiz",
//  "cpf": "11122233344",
//  "email": "luiz@example.com",
//  "senha": "1234",
//  "dataNasc": "2005-03-12",
//  "especializacao": "mixagem"
//  "eventoAuxiliado": "Taylor_Swift:_The_Eras_Tour"
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
    public ResponseEntity<String> criarStaff(@RequestBody Staff staff1){
        try {
            staffService.createStaff(staff1);
            return ResponseEntity.status(HttpStatus.CREATED).body("Membro do staff cadastrado com sucesso: " + staff1.getIdStaff());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizarStaff(@PathVariable int id, @RequestBody Staff staff1) {

        staffService.updateStaff(id, staff1);
        return ResponseEntity.ok("Membro do staff com o id:" + id + "atualizado com sucesso!!");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerStaff(@PathVariable int id){
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Membro do staff com ID " + id + " removido com sucesso!");
    }
}