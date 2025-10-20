package br.com.unit.controller;

import org.springframework.web.bind.annotation.*;
import br.com.unit.classes.Espectador;
import java.util.*;

//POST http://localhost:8080/staff
//{
//  "nome": "Luiz",
//  "cpf": "11122233344",
//  "email": "luiz@example.com",
//  "senha": "1234",
//  "dataNasc": "2005-03-12",
//  "idStaff": 7
//  "especializacao": "mixagem"
//  "eventoAuxiliado": "Taylor_Swift:_The_Eras_Tour"
//}

@RestController
@RequestMapping("/staff")
public class StaffController {

    private List<Staff> staff = new ArrayList<>();

    @GetMapping
    public List<Staff> listarStaff() { return staff; }

    @PostMapping
    public String criarStaff(@RequestBody Staff staff1){
        staff.add(staff1);
        return "Membro do staff cadastrado com sucesso: " + staff1.getIdStaff();
    }

    @Deletemapping("/{id}")
    public String removerStaff(@PathVariable int id){
        staff.removeIf(e -> e.getIdStaff() == id);
        return "Membro do staff com ID " + id + " removido com sucesso!";
    }
}