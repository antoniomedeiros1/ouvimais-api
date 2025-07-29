package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.service.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cidades")
@CrossOrigin
public class CidadeController {

    private final CidadeService service;

    public CidadeController(CidadeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Cidade> cidades = service.getCidades();
        return ResponseEntity.ok(cidades.stream().map(CidadeDTO::create).collect(Collectors.toList()));
    }

}
