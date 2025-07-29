package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.ReclamacaoDTO;
import br.ufjf.ouvimais_api.api.dto.ReclamacaoDTO;
import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import br.ufjf.ouvimais_api.service.ReclamacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Reclamacao> reclamacao = service.getReclamacaoById(id);
        if (!reclamacao.isPresent()){
            return new ResponseEntity("Reclamacao nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(reclamacao.map(ReclamacaoDTO::create));
    }

}
