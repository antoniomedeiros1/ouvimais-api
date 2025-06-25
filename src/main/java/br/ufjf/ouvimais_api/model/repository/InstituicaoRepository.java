package br.ufjf.ouvimais_api.model.repository;

import br.ufjf.ouvimais_api.model.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}
