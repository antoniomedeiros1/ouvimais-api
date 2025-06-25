package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamacaoDTO {
    private Long id;
    private String tipo;
    private String nome;
    private String descricao;
    private String status;
    private String timestamp;
    private Long idCidadao;
    private Long idSetor;

    public static ReclamacaoDTO create(Reclamacao reclamacao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reclamacao, ReclamacaoDTO.class);
    }
}
