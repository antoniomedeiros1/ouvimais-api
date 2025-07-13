package br.ufjf.ouvimais_api.service;

import br.ufjf.ouvimais_api.model.entity.Faq;
import br.ufjf.ouvimais_api.model.repository.FaqRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FaqService {

    private final FaqRepository repository;

    public FaqService(FaqRepository repository) { this.repository = repository; }

    public List<Faq> getFaqs() { return repository.findAll(); }

    public Optional<Faq> getFaqById(Long id) { return repository.findById(id); }

    public void save(Faq faq) {
        // validar regras de negocio
        repository.save(faq);
    }

    public void delete(Faq faq) {
        Objects.requireNonNull(faq.getId());
        repository.delete(faq);
    }

}
