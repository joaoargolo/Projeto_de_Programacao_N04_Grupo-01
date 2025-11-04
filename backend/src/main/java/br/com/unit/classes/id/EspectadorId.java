package br.com.unit.classes.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspectadorId implements Serializable {
    private Integer idPessoa;
    private Integer idEspectador;
}