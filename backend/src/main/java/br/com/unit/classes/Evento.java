package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_evento", discriminatorType = DiscriminatorType.STRING)
public abstract class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;

    @Column(nullable = false)
    private String nomeEvento;

    @Column(nullable = false)
    private String descricaoEvento;

    @Column(nullable = false)
    private String dataInicio;

    @Column(nullable = false)
    private String dataFim;

    @Column(nullable = false)
    private int capacidade;

    public void mostrarInformacaoEvento() {
        System.out.println("ID do evento: " + this.idEvento);
        System.out.println("Nome do evento: " + this.nomeEvento);
        System.out.println("Descrição do evento: " + this.descricaoEvento);
        System.out.println("Data de início: " + this.dataInicio);
        System.out.println("Data de fim: " + this.dataFim);
    }

    public void cadastrarEvento() {
        System.out.println("O evento " + this.nomeEvento + " foi cadastrado");
    }

    public void removerEvento() {
        System.out.println("O evento " + this.nomeEvento + " foi removido");
    }
}