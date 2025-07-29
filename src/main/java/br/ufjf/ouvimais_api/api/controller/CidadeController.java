package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.service.CidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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


    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = service.getCidadeById(id);
        if (!cidade.isPresent()){
            return new ResponseEntity("Cidade nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cidade.map(CidadeDTO::create));
    }

}
