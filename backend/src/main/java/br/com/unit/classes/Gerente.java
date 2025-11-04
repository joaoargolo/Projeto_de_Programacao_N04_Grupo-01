package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gerentes")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Gerente extends Pessoa {

    private Integer idGerente;

    public void cadastrarEvento() {
        System.out.println("O evento foi cadastrado.");
    }

    public void removerEvento() {
        System.out.println("O evento foi removido.");
    }
}