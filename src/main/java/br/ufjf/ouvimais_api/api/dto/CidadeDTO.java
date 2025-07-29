package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Cidade;
import org.modelmapper.ModelMapper;

public class CidadeDTO {
    private Long id;
    private String nome;
    private String estado;

    public static CidadeDTO create(Cidade cidade) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cidade, CidadeDTO.class);
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
