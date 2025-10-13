package br.com.unit.classes;

abstract class Evento {
	protected int idEvento;
	public String nomeEvento;
	public String descricaoEvento;
	public String dataInicio;
	public String dataFim;
	public int capacidade;
	
	protected Evento(int idEvento, String nomeEvento, String descricaoEvento, String dataInicio, String dataFim,
			int capacidade) {
		super();
		this.idEvento = idEvento;
		this.nomeEvento = nomeEvento;
		this.descricaoEvento = descricaoEvento;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.capacidade = capacidade;
	}

	protected int getIdEvento() {
		return idEvento;
	}

	protected String getNomeEvento() {
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
		
	}
	
	public void cadastrarEvento() {
		
	}
	
	public void removerEvento() {
		
	}
	
	
}

