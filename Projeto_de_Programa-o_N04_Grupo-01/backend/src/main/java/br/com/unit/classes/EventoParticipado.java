package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "evento_participado")
public class EventoParticipado extends Evento{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEventoParticipado;

    @Override
    public void mostrarInformacaoEvento() {
        System.out.println("Você participou do evento; "+this.getNomeEvento());
        System.out.println("Descrição do evento: " + this.getDescricaoEvento());
        System.out.println("Data de inicio: " + this.getDataInicio());
        System.out.println("Data de fim: " + this.getDataFim());
    }

}