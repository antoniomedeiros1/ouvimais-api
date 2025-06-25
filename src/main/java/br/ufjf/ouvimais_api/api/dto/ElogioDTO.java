package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Elogio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElogioDTO {
    private Long id;
    private String tipo;
    private String nota;
    private String descricao;
    private String status;
    private String timestamp;
    private Long idCidadao;
    private Long idSetor;

    public static ElogioDTO create(Elogio elogio) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(elogio, ElogioDTO.class);
    }
}
