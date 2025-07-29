package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.BairroDTO;
import br.ufjf.ouvimais_api.model.entity.Bairro;
import br.ufjf.ouvimais_api.service.BairroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bairros")
@CrossOrigin
public class BairroController {

    private final BairroService service;

    public BairroController(BairroService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Bairro> bairros = service.getBairros();
        return ResponseEntity.ok(bairros.stream().map(BairroDTO::create).collect(Collectors.toList()));
    }

}
