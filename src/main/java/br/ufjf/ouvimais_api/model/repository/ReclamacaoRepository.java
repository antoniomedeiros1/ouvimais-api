package br.ufjf.ouvimais_api.model.repository;

import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long> {

}
