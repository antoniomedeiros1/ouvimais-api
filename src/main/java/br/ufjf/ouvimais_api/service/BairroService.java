package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Bairro;
import br.ufjf.ouvimais_api.model.repository.BairroRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BairroService {

    private BairroRepository repository;

    public BairroService(BairroRepository repository) {
        this.repository = repository;
    }

    public List<Bairro> getBairros() {
        return repository.findAll();
    }

    public Optional<Bairro> getBairroById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Bairro save(Bairro bairro) {
        validate(bairro);
        return repository.save(bairro);
    }

    @Transactional
    public void delete(Bairro bairro) {
        Objects.requireNonNull(bairro.getId());
        repository.delete(bairro);
    }

    public void validate(Bairro bairro) {
        if (bairro.getNome() == null || bairro.getCidade() == null) {
            throw new RuntimeException("Invalid 'Bairro'");
        }
    }

}
