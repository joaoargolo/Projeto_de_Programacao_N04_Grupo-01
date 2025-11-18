package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staffs")
@EqualsAndHashCode(callSuper = true)
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "idStaff"
)
public class Staff extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStaff;

    @Column(nullable = false)
    private String especializacao;

    @ManyToMany(mappedBy = "staffs")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Evento> eventosAuxiliados;

    public void atribuirEvento(Evento evento) {
        System.out.println("O seguinte evento foi atribuído: " + evento.getNomeEvento());
    }

    public void removerEvento(Evento evento) {
        System.out.println("O seguinte evento foi removido: " + evento.getNomeEvento());
    }
}