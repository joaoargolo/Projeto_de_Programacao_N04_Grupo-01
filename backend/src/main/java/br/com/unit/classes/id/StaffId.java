package br.com.unit.classes.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffId implements Serializable {
    private Integer idPessoa;
    private Integer idStaff;
}