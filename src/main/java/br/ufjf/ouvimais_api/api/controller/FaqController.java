package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.FaqDTO;
import br.ufjf.ouvimais_api.model.entity.Faq;
import br.ufjf.ouvimais_api.service.FaqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

}
