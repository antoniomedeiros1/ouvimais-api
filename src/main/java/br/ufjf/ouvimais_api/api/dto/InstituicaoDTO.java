package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Instituicao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstituicaoDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String site;
    private String numero;
    private String complemento;
    private String idEndereco;

    public static InstituicaoDTO create(Instituicao instituicao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(instituicao, InstituicaoDTO.class);
    }
}
