package br.com.unit.dto;

public record CondutorDTO(
	int idCondutor,
	String nome,
	String cpf,
	String email,
	String senha,
	String dataNasc,
	String telefone,
	String perfil,
	java.util.List<Integer> eventosConduzidos
) {

}