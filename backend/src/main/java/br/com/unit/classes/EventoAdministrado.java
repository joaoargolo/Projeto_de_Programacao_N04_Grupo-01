package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evento_administrado")
public class EventoAdministrado extends Evento{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEventoAdministrado;

    @Override
    public void mostrarInformacaoEvento() {
         System.out.println("Você administrou o evento; "+this.getNomeEvento());
        System.out.println("ID do evento: " + this.getIdEvento());
        System.out.println("Descrição do evento: " + this.getDescricaoEvento());
        System.out.println("Data de inicio: " + this.getDataInicio());
        System.out.println("Data de fim: " + this.getDataFim());
    }
}