package br.com.unit.classes;

public class Staff extends Pessoa{
	private long idStaff;
	private String especializacao;
	private String eventoAuxiliado;

	protected Staff(String nome, String cpf, String email, String senha, String dataNasc, long idStaff,
			String especializacao, String eventoAuxiliado) {
		super(nome, cpf, email, senha, dataNasc);
		this.idStaff = idStaff;
		this.especializacao = especializacao;
		this.eventoAuxiliado = eventoAuxiliado;
	}

	public long getIdStaff() {
		return idStaff;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public String getEventoAuxiliado() {
		return eventoAuxiliado;
	}

	public void setEventoAuxiliado(String eventoAuxiliado) {
		this.eventoAuxiliado = eventoAuxiliado;
	}
	
	public void atribuirEvento() {
		System.out.println("O seguinte evento foi atribuido: ");
	}
	
	public void removerEvento() {
		System.out.println("O seguinte evento foi removido: ");
	}
	
}
