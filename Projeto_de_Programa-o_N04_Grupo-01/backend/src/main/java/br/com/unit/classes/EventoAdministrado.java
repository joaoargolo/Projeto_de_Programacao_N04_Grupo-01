package br.com.unit.classes;

public class EventoAdministrado extends Evento{

    protected EventoAdministrado(int idEvento, String nomeEvento, String descricaoEvento, String dataInicio, String dataFim, int capacidade) {
        super(idEvento, nomeEvento, descricaoEvento, dataInicio, dataFim, capacidade);
    }

    @Override
    public void mostrarInformacaoEvento() {
        System.out.println("Você administrou o evento; "+this.getNomeEvento());
        System.out.println("ID do evento: " + this.getIdEvento());
        System.out.println("Descrição do evento: " + this.getDescricaoEvento());
        System.out.println("Data de inicio: " + this.getDataInicio());
        System.out.println("Data de fim: " + this.getDataFim());
    }
}