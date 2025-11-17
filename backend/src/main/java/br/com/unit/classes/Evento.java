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
@Table(name = "eventos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_evento", discriminatorType = DiscriminatorType.STRING)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "idEvento"
)
public class Evento {

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

    @ManyToMany
    @JoinTable(
    name = "evento_staff",
    joinColumns = @JoinColumn(name = "idEvento"),
    inverseJoinColumns = @JoinColumn(name = "idStaff"))
    private List<Staff> staffs;

    @ManyToMany
    @JoinTable(
    name = "evento_espectador",
    joinColumns = @JoinColumn(name = "idEvento"),
    inverseJoinColumns = @JoinColumn(name = "idEspectador"))
    private List<Espectador> espectadores;

    @ManyToMany
    @JoinTable(
    name = "evento_condutor",
    joinColumns = @JoinColumn(name = "idEvento"),
    inverseJoinColumns = @JoinColumn(name = "idCondutor"))
    private List<Condutor> condutores;

    @ManyToOne
    @JoinColumn(name = "idGerente")
    private Gerente gerente;

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