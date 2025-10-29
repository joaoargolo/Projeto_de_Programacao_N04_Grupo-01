package br.com.unit.classes;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("PARTICIPADO")
public class EventoParticipado extends Evento{
    
    @Override
    public void mostrarInformacaoEvento() {
        System.out.println("Você participou do evento; "+this.getNomeEvento());
        System.out.println("Descrição do evento: " + this.getDescricaoEvento());
        System.out.println("Data de inicio: " + this.getDataInicio());
        System.out.println("Data de fim: " + this.getDataFim());
    }

}