package br.ufjf.ouvimais_api.model.repository;

import br.ufjf.ouvimais_api.model.entity.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

}
