package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.model.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository repository;

    public CidadeService(
        CidadeRepository repository
    ) {
        this.repository = repository;
    }

    public List<Cidade> getCidades() {
        return this.repository.findAll();
    }

    public Optional<Cidade> getCidadeById(Long id) {
        return this.repository.findById(id);
    }

    @Transactional
    public void save(Cidade cidade) {
        // validar regras de negocio
        this.repository.save(cidade);
    }

    @Transactional
    public void delete(Cidade cidade) {
        Objects.requireNonNull(cidade.getId());
        repository.delete(cidade);
    }
}
