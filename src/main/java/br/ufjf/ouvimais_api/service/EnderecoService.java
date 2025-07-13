package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Endereco;
import br.ufjf.ouvimais_api.model.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) { this.repository = repository; }

    public List<Endereco> getEnderecos() { return repository.findAll(); }

    public Optional<Endereco> getEnderecoById(Long id) { return repository.findById(id); }

    public void save(Endereco endereco) {
        // validar regras de negocio
        repository.save(endereco);
    }

    public void delete(Endereco endereco) {
        Objects.requireNonNull(endereco.getId());
        repository.delete(endereco);
    }

}
