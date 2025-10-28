package br.com.unit.classes;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Evento {
    protected int idEvento;
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
