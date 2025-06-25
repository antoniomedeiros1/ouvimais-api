package br.ufjf.ouvimais_api.model.repository;

import br.ufjf.ouvimais_api.model.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

}
