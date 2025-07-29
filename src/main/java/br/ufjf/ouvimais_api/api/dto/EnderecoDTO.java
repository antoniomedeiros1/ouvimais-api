package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Endereco;
import org.modelmapper.ModelMapper;

public class EnderecoDTO {
    private Long id;
    private String logradouro;
    private String cep;
    private Long idBairro;

    public static EnderecoDTO create(Endereco endereco) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(endereco, EnderecoDTO.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Long idBairro) {
        this.idBairro = idBairro;
    }
}
