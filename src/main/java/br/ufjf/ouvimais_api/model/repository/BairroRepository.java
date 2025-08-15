package br.ufjf.ouvimais_api.model.repository;

import br.ufjf.ouvimais_api.model.entity.Bairro;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    List<Bairro> findByCidade(Optional<Cidade> cidade);
}
