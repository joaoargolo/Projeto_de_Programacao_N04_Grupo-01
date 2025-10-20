package br.com.unit.classes;

public class EventoParticipado extends Evento{

    protected EventoParticipado(int idEvento, String nomeEvento, String descricaoEvento, String dataInicio, String dataFim, int capacidade) {
        super(idEvento, nomeEvento, descricaoEvento, dataInicio, dataFim, capacidade);
    }

    @Override
    public void mostrarInformacaoEvento() {
        System.out.println("Você participou do evento; "+this.getNomeEvento());
        System.out.println("Descrição do evento: " + this.getDescricaoEvento());
        System.out.println("Data de inicio: " + this.getDataInicio());
        System.out.println("Data de fim: " + this.getDataFim());
    }

}