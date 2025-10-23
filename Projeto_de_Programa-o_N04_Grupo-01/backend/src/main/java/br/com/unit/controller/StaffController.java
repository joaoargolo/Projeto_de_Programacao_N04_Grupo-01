package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Staff;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    private List<Staff> staff = new ArrayList<>();

    @GetMapping("/listar")
    public List<Staff> listarStaff() { return staff; }

    @PostMapping("/criar")
    public ResponseEntity<String> criarStaff(@RequestBody Staff staff1){
        staff.add(staff1);
        return ResponseEntity.status(HttpStatus.CREATED).body("Membro do staff cadastrado com sucesso: " + staff1.getIdStaff());
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerStaff(@PathVariable int id){
        staff.removeIf(e -> e.getIdStaff() == id);
        return ResponseEntity.ok("Membro do staff com ID " + id + " removido com sucesso!");
    }
}