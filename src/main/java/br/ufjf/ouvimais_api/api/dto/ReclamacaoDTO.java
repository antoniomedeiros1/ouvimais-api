package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import org.modelmapper.ModelMapper;

public class ReclamacaoDTO {
    private Long id;
    private String tipo;
    private String descricao;
    private String status;
    private String timestamp;
    private Long idCidadao;
    private Long idSetor;
    private Long idEndereco;

    public static ReclamacaoDTO create(Reclamacao reclamacao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reclamacao, ReclamacaoDTO.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getIdCidadao() {
        return idCidadao;
    }

    public void setIdCidadao(Long idCidadao) {
        this.idCidadao = idCidadao;
    }

    public Long getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Long idSetor) {
        this.idSetor = idSetor;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(long idEndereco) {
        this.idEndereco = idEndereco;
    }
}
