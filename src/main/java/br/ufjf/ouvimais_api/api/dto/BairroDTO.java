package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Bairro;
import org.modelmapper.ModelMapper;

public class BairroDTO {
    private Long id;
    private String nome;
    private Long idCidade;

    public static BairroDTO create(Bairro bairro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bairro, BairroDTO.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }
}
