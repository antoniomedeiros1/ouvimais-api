package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Funcionario;
import br.ufjf.ouvimais_api.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) { this.repository = repository; }

    public List<Funcionario> getFuncionarios() { return repository.findAll(); }

    public Optional<Funcionario> getFuncionarioById(Long id) { return repository.findById(id); }

    public void save(Funcionario funcionario) {
        // validar regras de negocio
        repository.save(funcionario);
    }

    public void delete(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getId());
        repository.delete(funcionario);
    }

}
