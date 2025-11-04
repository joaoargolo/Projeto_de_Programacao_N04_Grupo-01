package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;
import br.com.unit.classes.id.GerenteId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gerentes")
@EqualsAndHashCode(callSuper = true)
@IdClass(GerenteId.class)
public class Gerente extends Pessoa {

    @Id
    @Column(name = "idGerente")
    private Integer idGerente;

    public void cadastrarEvento() {
        System.out.println("O evento foi cadastrado.");
    }

    public void removerEvento() {
        System.out.println("O evento foi removido.");
    }
}