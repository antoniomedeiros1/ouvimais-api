package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadaoDTO;
import br.ufjf.ouvimais_api.model.entity.Cidadao;
import br.ufjf.ouvimais_api.service.CidadaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cidadaos")
@CrossOrigin
public class CidadaoController {

    private final CidadaoService service;

    public CidadaoController(CidadaoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Cidadao> cidadaos = service.getCidadaos();
        return ResponseEntity.ok(cidadaos.stream().map(CidadaoDTO::create).collect(Collectors.toList()));
    }
}
