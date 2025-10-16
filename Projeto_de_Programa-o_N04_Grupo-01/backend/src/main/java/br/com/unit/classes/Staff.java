package br.com.unit.classes;

public class Staff extends Pessoa{
	private int idStaff; 
	private String especializacao;
	private String eventoAuxiliado;

	protected Staff(String nome, String cpf, String email, String senha, String dataNasc, int idStaff,
			String especializacao, String eventoAuxiliado) {
		super(nome, cpf, email, senha, dataNasc);
		this.idStaff = idStaff;
		this.especializacao = especializacao;
		this.eventoAuxiliado = eventoAuxiliado;
	}

	private int getIdStaff() {
		return idStaff;
	}

	private String getEspecializacao() {
		return especializacao;
	}

	private String getEventoAuxiliado() {
		return eventoAuxiliado;
	}

	private void setEventoAuxiliado(String eventoAuxiliado) {
		this.eventoAuxiliado = eventoAuxiliado;
	}
	
	public void atribuirEvento() {
		System.out.println("O seguinte evento foi atribuido: ");
	}
	
	public void removerEvento() {
		System.out.println("O seguinte evento foi removido: ");
	}
	
}
