package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Bairro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BairroDTO {
    private Long id;
    private String nome;
    private Long idCidade;

    public static BairroDTO create(Bairro bairro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bairro, BairroDTO.class);
    }
}
