package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;
import br.com.unit.classes.id.StaffId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staffs")
@EqualsAndHashCode(callSuper = true)
@IdClass(StaffId.class)
public class Staff extends Pessoa {

    @Id
    @Column(name = "idStaff")
    private Integer idStaff;

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
