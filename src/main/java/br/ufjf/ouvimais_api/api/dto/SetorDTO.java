package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Setor;
import org.modelmapper.ModelMapper;

public class SetorDTO {
    private Long id;
    private String nome;
    private Long idInstituicao;

    public static SetorDTO create(Setor setor) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(setor, SetorDTO.class);
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

    public Long getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }
}
