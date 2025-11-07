package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "condutores")
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idCondutor"
)
public class Condutor extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCondutor;

    @ManyToMany(mappedBy = "condutores")
    private List<Evento> eventosConduzidos;

    public void conduzirEvento(Evento evento) {
        System.out.println("O condutor está conduzindo o evento: " + evento.getNomeEvento());
    }

    public void removerEvento(Evento evento) {
        System.out.println("A participação do condutor foi removida do evento: " + evento.getNomeEvento());
    }
}