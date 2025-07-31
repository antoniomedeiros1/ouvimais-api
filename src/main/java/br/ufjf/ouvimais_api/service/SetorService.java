package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Setor;
import br.ufjf.ouvimais_api.model.repository.SetorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SetorService {

    private final SetorRepository repository;

    public SetorService(SetorRepository repository) { this.repository = repository; }

    public List<Setor> getSetors() { return repository.findAll(); }

    public Optional<Setor> getSetorById(Long id) { return repository.findById(id); }

    public Setor save(Setor setor) {
        // validar regras de negocio
        return repository.save(setor);
    }

    public void delete(Setor setor) {
        Objects.requireNonNull(setor.getId());
        repository.delete(setor);
    }

}
