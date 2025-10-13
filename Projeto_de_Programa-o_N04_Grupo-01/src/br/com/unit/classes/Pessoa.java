package br.com.unit.classes;


abstract class Pessoa {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String dataNasc;
    
	protected Pessoa(String nome, String cpf, String email, String senha, String dataNasc) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.dataNasc = dataNasc;
	}

	private String getNome() {
		return nome;
	}

	private String getCpf() {
		return cpf;
	}

	private String getEmail() {
		return email;
	}

	private String getSenha() {
		return senha;
	}

	private String getDataNasc() {
		return dataNasc;
	}
    
}
