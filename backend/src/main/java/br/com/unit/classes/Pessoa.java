package br.com.unit.classes;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Pessoa {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 60)
    private String senha;

    @Column(nullable = false)
    private String dataNasc;

    @Column(nullable = true)
    private String telefone;

    @Column(nullable = true)
    private String perfil;

    public enum Status {
        ATIVO,
        INATIVO,
        PENDENTE_DE_CONFIRMACAO
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Status status = Status.PENDENTE_DE_CONFIRMACAO;

    public void atualizarStatusAutomaticamente() {
        boolean faltandoCampoObrigatorio =
                isCampoVazio(this.nome) ||
                        isCampoVazio(this.cpf) ||
                        isCampoVazio(this.email) ||
                        isCampoVazio(this.senha) ||
                        isCampoVazio(this.dataNasc) ||
                        isCampoVazio(this.telefone);

        if (faltandoCampoObrigatorio) {
            this.status = Status.PENDENTE_DE_CONFIRMACAO;
            return;
        }

        if (this.status == Status.INATIVO) {
            return;
        }

        this.status = Status.ATIVO;
    }



    private boolean isCampoVazio(String campo) {
        return campo == null || campo.trim().isEmpty();
    }

    public void desativar() {
        this.status = Status.INATIVO;
    }
}
