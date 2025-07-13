package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Elogio;
import br.ufjf.ouvimais_api.model.repository.ElogioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ElogioService {

    private final ElogioRepository repository;

    public ElogioService(ElogioRepository repository) { this.repository = repository; }

    public List<Elogio> getElogios() { return repository.findAll(); }

    public Optional<Elogio> getElogioById(Long id) { return repository.findById(id); }

    public void save(Elogio elogio) {
        // validar regras de negocio
        repository.save(elogio);
    }

    public void delete(Elogio elogio) {
        Objects.requireNonNull(elogio.getId());
        repository.delete(elogio);
    }

}
