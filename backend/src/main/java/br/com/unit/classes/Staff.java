package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staffs")
@EqualsAndHashCode(callSuper = true)
@IdClass(Staff.class)
public class Staff extends Pessoa {

    @Id
    private Integer idStaff;

    @Id
    private Integer idPessoa;

    @Column(nullable = false)
    private String especializacao;

    @Column(nullable = true)
    private String eventoAuxiliado;
    
    public void atribuirEvento() {
        System.out.println("O seguinte evento foi atribuído: " + eventoAuxiliado);
    }

    public void removerEvento() {
        System.out.println("O seguinte evento foi removido: " + eventoAuxiliado);
    }
}
