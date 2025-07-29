package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Faq;
import org.modelmapper.ModelMapper;

public class FaqDTO {
    private Long id;
    private String pergunta;
    private String resposta;
    private String idInstituicao;


    public static FaqDTO create(Faq faq) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(faq, FaqDTO.class);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(String idInstituicao) {
        this.idInstituicao = idInstituicao;
    }
}
