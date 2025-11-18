package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalDateTime;

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

    @ManyToMany(mappedBy = "espectadores")
    private List<Evento> eventosDoEspectador;

    public void cadastrarEvento() {
        System.out.println("O usuário foi cadastrado no evento.");
    }

    public void sairEvento() {
        System.out.println("O usuário foi retirado do evento");
    }

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

}
