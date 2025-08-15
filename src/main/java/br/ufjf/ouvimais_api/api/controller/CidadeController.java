package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.BairroDTO;
import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.model.entity.Bairro;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.service.BairroService;
import br.ufjf.ouvimais_api.service.CidadeService;
import org.modelmapper.ModelMapper;
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
    private final BairroService bairroService;

    public CidadeController(CidadeService service, BairroService bairroService) {
        this.service = service;
        this.bairroService = bairroService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Cidade> cidades = service.getCidades();
        return ResponseEntity.ok(cidades.stream().map(CidadeDTO::create).collect(Collectors.toList()));
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = service.getCidadeById(id);
        if (!cidade.isPresent()){
            return new ResponseEntity("Cidade nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cidade.map(CidadeDTO::create));
    }

    @GetMapping("/{id}/bairros")
    public ResponseEntity getBairros(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = service.getCidadeById(id);
        if (!cidade.isPresent()){
            return new ResponseEntity("Cidade nao encontrado", HttpStatus.NOT_FOUND);
        }
        List<Bairro> bairros = bairroService.getBairrosByCidade(cidade);
        return ResponseEntity.ok(bairros.stream().map(BairroDTO::create).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CidadeDTO dto) {
        try {
            Cidade cidade = convert(dto);
            cidade = service.save(cidade);
            return new ResponseEntity(cidade, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CidadeDTO dto) {
        if (!service.getCidadeById(id).isPresent()) {
            return new ResponseEntity("Cidade não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Cidade cidade = convert(dto);
            cidade.setId(id);
            service.save(cidade);
            return ResponseEntity.ok(cidade);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = service.getCidadeById(id);
        if (!cidade.isPresent()) {
            return new ResponseEntity("Cidade não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.delete(cidade.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Cidade convert(CidadeDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Cidade cidade = modelMapper.map(dto, Cidade.class);
        return cidade;
    }

}
