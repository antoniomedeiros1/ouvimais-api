package br.ufjf.ouvimais_api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reclamacao extends Ocorrencia {

    private String tipo;

    @ManyToOne
    private Endereco endereco;
}
