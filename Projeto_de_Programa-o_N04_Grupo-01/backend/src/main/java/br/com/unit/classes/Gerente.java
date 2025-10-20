package br.com.unit.classes;

public class Gerente extends Pessoa{
    private int idGerente;

    protected Gerente(String nome, String cpf, String email, String senha, String dataNasc, int idGerente) {
        super(nome, cpf, email, senha, dataNasc);
        this.idGerente = idGerente;
    }

    private int getIdGerente() {
        return idGerente;
    }

    public void cadastrarEvento() {
        System.out.println("O evento foi cadastrado");
    }

    public void removerEvento() {
        System.out.println("O evento foi removido.");
    }

}