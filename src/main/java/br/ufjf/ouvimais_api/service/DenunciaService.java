package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Denuncia;
import br.ufjf.ouvimais_api.model.repository.DenunciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DenunciaService {

    private final DenunciaRepository repository;

    public DenunciaService(DenunciaRepository repository) { this.repository = repository; }

    public List<Denuncia> getDenuncias() { return repository.findAll(); }

    public Optional<Denuncia> getDenunciaById(Long id) { return repository.findById(id); }

    public void save(Denuncia denuncia) {
        // validar regras de negocio
        repository.save(denuncia);
    }

    public void delete(Denuncia denuncia) {
        Objects.requireNonNull(denuncia.getId());
        repository.delete(denuncia);
    }

}
