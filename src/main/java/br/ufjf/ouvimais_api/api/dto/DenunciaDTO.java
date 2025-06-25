package br.ufjf.ouvimais_api.api.dto;

import br.ufjf.ouvimais_api.model.entity.Denuncia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaDTO {
    private Long id;
    private String tipo;
    private String descricao;
    private String status;
    private String timestamp;
    private Long idCidadao;
    private Long idSetor;

    public static DenunciaDTO create(Denuncia denuncia) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(denuncia, DenunciaDTO.class);
    }
}
