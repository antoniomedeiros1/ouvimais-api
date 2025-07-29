package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.ReclamacaoDTO;
import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import br.ufjf.ouvimais_api.service.ReclamacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reclamacoes")
@CrossOrigin
public class ReclamacaoController {

    private final ReclamacaoService service;

    public ReclamacaoController(ReclamacaoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Reclamacao> reclamacoes = service.getReclamacaos();
        return ResponseEntity.ok(reclamacoes.stream().map(ReclamacaoDTO::create).collect(Collectors.toList()));
    }

}
