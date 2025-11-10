package br.com.unit.dto;

import java.util.List;

public class StaffInputDTO {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String dataNasc;
    private String telefone;
    private String perfil;
    private String especializacao;
    private List<Integer> eventosAuxiliados;

    public StaffInputDTO() {}

    // getters and setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getDataNasc() { return dataNasc; }
    public void setDataNasc(String dataNasc) { this.dataNasc = dataNasc; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public String getEspecializacao() { return especializacao; }
    public void setEspecializacao(String especializacao) { this.especializacao = especializacao; }
    public List<Integer> getEventosAuxiliados() { return eventosAuxiliados; }
    public void setEventosAuxiliados(List<Integer> eventosAuxiliados) { this.eventosAuxiliados = eventosAuxiliados; }
}
