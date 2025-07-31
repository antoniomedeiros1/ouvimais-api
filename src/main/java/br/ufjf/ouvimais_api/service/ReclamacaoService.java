package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import br.ufjf.ouvimais_api.model.repository.ReclamacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReclamacaoService {

    private final ReclamacaoRepository repository;

    public ReclamacaoService(ReclamacaoRepository repository) { this.repository = repository; }

    public List<Reclamacao> getReclamacaos() { return repository.findAll(); }

    public Optional<Reclamacao> getReclamacaoById(Long id) { return repository.findById(id); }

    public Reclamacao save(Reclamacao reclamacao) {
        // validar regras de negocio
        return repository.save(reclamacao);
    }

    public void delete(Reclamacao reclamacao) {
        Objects.requireNonNull(reclamacao.getId());
        repository.delete(reclamacao);
    }

}
