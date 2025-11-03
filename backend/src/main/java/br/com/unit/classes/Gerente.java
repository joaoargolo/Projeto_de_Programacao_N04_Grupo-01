package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gerentes")
@EqualsAndHashCode(callSuper = true)
@IdClass(Gerente.class)
public class Gerente extends Pessoa {

    @Id
    private Integer idGerente;

    @Id
    private Integer idPessoa;

    public void cadastrarEvento() {
        System.out.println("O evento foi cadastrado.");
    }

    public void removerEvento() {
        System.out.println("O evento foi removido.");
    }
}