package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Instituicao;
import br.ufjf.ouvimais_api.model.repository.InstituicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class InstituicaoService {

    private final InstituicaoRepository repository;

    public InstituicaoService(InstituicaoRepository repository) { this.repository = repository; }

    public List<Instituicao> getInstituicaos() { return repository.findAll(); }

    public Optional<Instituicao> getInstituicaoById(Long id) { return repository.findById(id); }

    public void save(Instituicao instituicao) {
        // validar regras de negocio
        repository.save(instituicao);
    }

    public void delete(Instituicao instituicao) {
        Objects.requireNonNull(instituicao.getId());
        repository.delete(instituicao);
    }

}
