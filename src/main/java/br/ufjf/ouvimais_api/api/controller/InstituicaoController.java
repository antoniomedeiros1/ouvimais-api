package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.InstituicaoDTO;
import br.ufjf.ouvimais_api.model.entity.Instituicao;
import br.ufjf.ouvimais_api.service.InstituicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/instituicoes")
@CrossOrigin
public class InstituicaoController {

    private final InstituicaoService service;

    public InstituicaoController(InstituicaoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Instituicao> instituicoes = service.getInstituicaos();
        return ResponseEntity.ok(instituicoes.stream().map(InstituicaoDTO::create).collect(Collectors.toList()));
    }

}
