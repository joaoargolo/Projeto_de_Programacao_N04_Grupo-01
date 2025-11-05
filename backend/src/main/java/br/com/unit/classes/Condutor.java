package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "condutores")
@EqualsAndHashCode(callSuper = true)
public class Condutor extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCondutor;

    @Column(nullable = false)
    private String eventoConduzido;

    public void conduzirEvento() {
        System.out.println("O condutor está conduzindo o evento: " + eventoConduzido);
    }

    public void removerEvento() {
        System.out.println("A participação do condutor foi removida do evento: " + eventoConduzido);
    }
}