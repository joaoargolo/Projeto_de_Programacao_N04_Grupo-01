package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "espectadores")
@EqualsAndHashCode(callSuper = true)
@IdClass(Espectador.class)
public class Espectador extends Pessoa {

    @Id
    private Integer idEspectador;

    @Id
    private Integer idPessoa;

    public void cadastrarEvento() {
        System.out.println("O usuário foi cadastrado no evento.");
    }

    public void sairEvento() {
        System.out.println("O usuário foi retirado do evento");
    }
}
