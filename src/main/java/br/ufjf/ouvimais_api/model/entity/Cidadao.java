package br.ufjf.ouvimais_api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cidadao extends User {

    private String cpf;
    private String telefone;
    private String dataNasc;

    @ManyToOne
    private Endereco endereco;
}
