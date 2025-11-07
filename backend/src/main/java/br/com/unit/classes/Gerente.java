package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gerentes")
@EqualsAndHashCode(callSuper = true)
public class Gerente extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGerente;

    @OneToMany(mappedBy = "gerente")
    @JsonIgnore
    private List<Evento> eventosGerenciados;

    @JsonProperty("eventosGerenciados")
    public List<Integer> getEventosIds() {
        if (eventosGerenciados == null) return List.of();
        return eventosGerenciados.stream()
                .map(Evento::getIdEvento)
                .collect(Collectors.toList());
    }

    public void cadastrarEvento(Evento evento) {
        System.out.println("O evento '" + evento.getNomeEvento() + "' foi cadastrado pelo gerente.");
    }

    public void removerEvento(Evento evento) {
        System.out.println("O evento '" + evento.getNomeEvento() + "' foi removido pelo gerente.");
    }
}