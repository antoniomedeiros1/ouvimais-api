package br.ufjf.ouvimais_api.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
public class Cidadao extends User {

    private String cpf;
    private String telefone;
    private String dataNasc;
    private String numero;
    private String complemento;

    @ManyToOne
    private Endereco endereco;
}
