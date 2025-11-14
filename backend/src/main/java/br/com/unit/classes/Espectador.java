package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> 790a6f9bb50b4c4f62bd6a5ba0b1123470d8f939

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
        INATIVO,
        PENDENTE_DE_CONFIRMACAO
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.INATIVO;

    @ManyToMany(mappedBy = "espectadores")
    private List<Evento> eventosDoEspectador;

    public void atualizarStatus() {
        if (eventosDoEspectador == null || eventosDoEspectador.isEmpty()) {
            this.status = Status.INATIVO;
        } else {
            this.status = Status.ATIVO;
        }
    }

    public void cadastrarEvento() {
        System.out.println("O usuário foi cadastrado no evento.");
    }

    public void sairEvento() {
        System.out.println("O usuário foi retirado do evento");
    }
<<<<<<< HEAD
}
=======

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

}
>>>>>>> 790a6f9bb50b4c4f62bd6a5ba0b1123470d8f939
