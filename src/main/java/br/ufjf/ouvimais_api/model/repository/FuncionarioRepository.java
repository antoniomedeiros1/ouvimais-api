package br.ufjf.ouvimais_api.model.repository;

import br.ufjf.ouvimais_api.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
