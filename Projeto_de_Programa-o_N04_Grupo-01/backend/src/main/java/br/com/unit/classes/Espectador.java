package br.com.unit.classes;

public class Espectador extends Pessoa {
    private long idEspectador;

    protected Espectador(String nome, String cpf, String email, String senha, String dataNasc, long idEspectador) {
        super(nome, cpf, email, senha, dataNasc);
        this.idEspectador = idEspectador;
    }

    public long getIdEspectador() {
        return idEspectador;
    }

    public void cadastrarEvento() {
        System.out.println("O usuário foi cadastrado no evento.");
    }

    public void sairEvento() {
        System.out.println("O usuário foi retirado do evento");
    }
}
