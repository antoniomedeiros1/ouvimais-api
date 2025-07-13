package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Cidadao;
import br.ufjf.ouvimais_api.model.repository.CidadaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CidadaoService {

    private final CidadaoRepository repository;

    public CidadaoService(CidadaoRepository repository) { this.repository = repository; }

    public List<Cidadao> getCidadaos() { return repository.findAll(); }

    public Optional<Cidadao> getCidadaoById(Long id) { return repository.findById(id); }

    public void save(Cidadao cidadao) {
        // validar regras de negocio
        repository.save(cidadao);
    }

    public void delete(Cidadao cidadao) {
        Objects.requireNonNull(cidadao.getId());
        repository.delete(cidadao);
    }

}
