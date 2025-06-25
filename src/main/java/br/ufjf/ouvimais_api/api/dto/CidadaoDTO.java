package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Cidadao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CidadaoDTO {
    private Long Id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String dataNasc;
    private String numero;
    private String complemento;
    private String idEndereco;

    public static CidadaoDTO create(Cidadao cidadao) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(cidadao, CidadaoDTO.class);
    }
}
