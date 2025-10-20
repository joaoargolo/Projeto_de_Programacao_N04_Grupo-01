package br.com.unit.classes;

abstract class Evento {
    protected long idEvento;
    public String nomeEvento;
    public String descricaoEvento;
    public String dataInicio;
    public String dataFim;
    public int capacidade;

    protected Evento(long idEvento, String nomeEvento, String descricaoEvento, String dataInicio, String dataFim,
                     int capacidade) {
        super();
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descricaoEvento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.capacidade = capacidade;
    }

    public long getIdEvento() {
        return idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    protected String getDescricaoEvento() {
        return descricaoEvento;
    }

    protected String getDataInicio() {
        return dataInicio;
    }

    protected String getDataFim() {
        return dataFim;
    }

    protected int getCapacidade() {
        return capacidade;
    }

    protected void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    protected void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    protected void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    protected void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    protected void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    protected void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

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
