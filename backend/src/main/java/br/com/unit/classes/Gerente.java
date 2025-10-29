package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gerentes")
public class Gerente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGerente;

    public void cadastrarEvento() {
        System.out.println("O evento foi cadastrado.");
    }

    public void removerEvento() {
        System.out.println("O evento foi removido.");
    }
}