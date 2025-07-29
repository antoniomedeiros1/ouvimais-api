package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.FaqDTO;
import br.ufjf.ouvimais_api.api.dto.FaqDTO;
import br.ufjf.ouvimais_api.model.entity.Faq;
import br.ufjf.ouvimais_api.model.entity.Faq;
import br.ufjf.ouvimais_api.service.FaqService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/faqs")
@CrossOrigin
public class FaqController {

    private final FaqService service;

    public FaqController(FaqService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Faq> faqs = service.getFaqs();
        return ResponseEntity.ok(faqs.stream().map(FaqDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Faq> faq = service.getFaqById(id);
        if (!faq.isPresent()){
            return new ResponseEntity("Faq nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(faq.map(FaqDTO::create));
    }

}
