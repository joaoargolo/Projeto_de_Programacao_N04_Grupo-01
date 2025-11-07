package br.com.unit.dto;

public class GerenteInputDTO {
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String dataNasc;
    private String telefone;
    private String perfil;
    private java.util.List<Integer> eventosGerenciados;

    public GerenteInputDTO() {}

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
    public java.util.List<Integer> getEventosGerenciados() { return eventosGerenciados; }
    public void setEventosGerenciados(java.util.List<Integer> eventosGerenciados) { this.eventosGerenciados = eventosGerenciados; }
}
