package br.com.unit.dto;

public record GerenteDTO(
	int idGerente,
	String nome,
	String cpf,
	String email,
	String senha,
	String dataNasc,
	String telefone,
	String perfil,
	java.util.List<Integer> eventosGerenciados
) {

}
