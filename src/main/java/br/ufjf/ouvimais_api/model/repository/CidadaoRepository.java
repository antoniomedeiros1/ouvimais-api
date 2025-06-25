package br.ufjf.ouvimais_api.model.repository;

import br.ufjf.ouvimais_api.model.entity.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {

}
