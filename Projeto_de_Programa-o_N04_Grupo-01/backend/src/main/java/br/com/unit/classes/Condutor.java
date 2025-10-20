package br.com.unit.classes;

public class Condutor extends Pessoa {
    private int idCondutor;
    private String eventoConduzido;

    protected Condutor(String nome, String cpf, String email, String senha, String dataNasc,
                       int idCondutor, String eventoConduzido) {
        super(nome, cpf, email, senha, dataNasc);
        this.idCondutor = idCondutor;
        this.eventoConduzido = eventoConduzido;
    }

    public int getIdCondutor() {
        return idCondutor;
    }

    public String getEventoConduzido() {
        return eventoConduzido;
    }

    public void conduzirEvento() {
        System.out.println("O condutor esta conduzindo o seguinte evento: ");
    }

    public void removerEvento() {
        System.out.println("A participação do condutor foi removida do evento: ");
    }
}
