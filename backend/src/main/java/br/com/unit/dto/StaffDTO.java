package br.com.unit.dto;

public record StaffDTO(
	int idStaff,
	String nome,
	String cpf,
	String email,
	String senha,
	String dataNasc,
	String telefone,
	String perfil,
	String especializacao,
	java.util.List<Integer> eventosAuxiliados
) {

}
