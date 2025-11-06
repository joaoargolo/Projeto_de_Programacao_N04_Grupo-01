package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "espectadores")
@EqualsAndHashCode(callSuper = true)
public class Espectador extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspectador;

    public enum Status {
        ATIVO,
        INATIVO
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.INATIVO;

    public void cadastrarEvento() {
        System.out.println("O usuário foi cadastrado no evento.");
    }

    public void sairEvento() {
        System.out.println("O usuário foi retirado do evento");
    }
}
