package br.com.unit.classes;

public class Gerente extends Pessoa{
    private long idGerente;

    protected Gerente(String nome, String cpf, String email, String senha, String dataNasc, long idGerente) {
        super(nome, cpf, email, senha, dataNasc);
        this.idGerente = idGerente;
    }

    public long getIdGerente() {
        return idGerente;
    }

    public void cadastrarEvento() {
        System.out.println("O evento foi cadastrado");
    }

    public void removerEvento() {
        System.out.println("O evento foi removido.");
    }

}