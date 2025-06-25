package br.ufjf.ouvimais_api.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Elogio extends Ocorrencia {

    private String tipo;
    private String nota;
}
