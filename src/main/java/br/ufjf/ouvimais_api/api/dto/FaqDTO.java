package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Faq;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqDTO {
    private Long id;
    private String pergunta;
    private String resposta;
    private String idInstituicao;


    public static FaqDTO create(Faq faq) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(faq, FaqDTO.class);
    }
}
