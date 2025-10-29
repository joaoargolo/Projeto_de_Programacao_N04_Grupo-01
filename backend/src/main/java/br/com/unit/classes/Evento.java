package br.com.unit.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_evento", discriminatorType = DiscriminatorType.STRING)

public abstract class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvento;
    public String nomeEvento;
    public String descricaoEvento;
    public String dataInicio;
    public String dataFim;
    public int capacidade;

    public void mostrarInformacaoEvento() {
        System.out.println("ID do evento: " + this.idEvento);
        System.out.println("Nome do evento: " + this.nomeEvento);
        System.out.println("Descrição do evento: " + this.descricaoEvento);
        System.out.println("Data de inicio: " + this.dataInicio);
        System.out.println("Data de fim: " + this.dataFim);
    }

    public void cadastrarEvento() {
        System.out.println("O evento " + this.nomeEvento + " foi cadastrado");
    }

    public void removerEvento() {
        System.out.println("O evento " + this.nomeEvento + " foi removido");
    }

}
