package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.BairroDTO;
import br.ufjf.ouvimais_api.api.dto.CidadaoDTO;
import br.ufjf.ouvimais_api.model.entity.Bairro;
import br.ufjf.ouvimais_api.model.entity.Cidadao;
import br.ufjf.ouvimais_api.service.CidadaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cidadao> cidadao = service.getCidadaoById(id);
        if (!cidadao.isPresent()){
            return new ResponseEntity("Cidadao nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cidadao.map(CidadaoDTO::create));
    }
}
