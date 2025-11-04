package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "espectadores")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "idPessoa")
public class Espectador extends Pessoa {

    private Integer idEspectador;

    public void cadastrarEvento() {
        System.out.println("O usuário foi cadastrado no evento.");
    }

    public void sairEvento() {
        System.out.println("O usuário foi retirado do evento");
    }
}