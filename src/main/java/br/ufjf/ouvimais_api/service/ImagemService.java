package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Imagem;
import br.ufjf.ouvimais_api.model.repository.ImagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImagemService {

    private final ImagemRepository repository;

    public ImagemService(ImagemRepository repository) { this.repository = repository; }

    public List<Imagem> getImagems() { return repository.findAll(); }

    public Optional<Imagem> getImagemById(Long id) { return repository.findById(id); }

    public void save(Imagem imagem) {
        // validar regras de negocio
        repository.save(imagem);
    }

    public void delete(Imagem imagem) {
        Objects.requireNonNull(imagem.getId());
        repository.delete(imagem);
    }

}
