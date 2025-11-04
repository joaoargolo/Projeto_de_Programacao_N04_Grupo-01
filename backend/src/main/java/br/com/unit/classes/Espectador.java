package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;
import br.com.unit.classes.id.EspectadorId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "espectadores")
@EqualsAndHashCode(callSuper = true)
@IdClass(EspectadorId.class)
public class Espectador extends Pessoa {

    @Id
    @Column(name = "idEspectador")
    private Integer idEspectador;

    public void cadastrarEvento() {
        System.out.println("O usuário foi cadastrado no evento.");
    }

    public void sairEvento() {
        System.out.println("O usuário foi retirado do evento");
    }
}
