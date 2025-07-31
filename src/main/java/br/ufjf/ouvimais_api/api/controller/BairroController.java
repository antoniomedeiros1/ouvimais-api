package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.BairroDTO;
import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.model.entity.Bairro;
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
@RequestMapping("/api/v1/bairros")
@CrossOrigin
public class BairroController {

    private final BairroService service;
    private final CidadeService cidadeService;

    public BairroController(
            BairroService service,
            CidadeService cidadeService
    ) {
        this.service = service;
        this.cidadeService = cidadeService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Bairro> bairros = service.getBairros();
        return ResponseEntity.ok(bairros.stream().map(BairroDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Bairro> bairro = service.getBairroById(id);
        if (!bairro.isPresent()){
            return new ResponseEntity("Bairro nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(bairro.map(BairroDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody BairroDTO dto) {
        try {
            Bairro bairro = convert(dto);
            bairro = service.save(bairro);
            return new ResponseEntity(bairro, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody BairroDTO dto) {
        if (!service.getBairroById(id).isPresent()) {
            return new ResponseEntity("Bairro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Bairro bairro = convert(dto);
            bairro.setId(id);
            service.save(bairro);
            return ResponseEntity.ok(bairro);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Bairro> bairro = service.getBairroById(id);
        if (!bairro.isPresent()) {
            return new ResponseEntity("Bairro não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.delete(bairro.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Bairro convert(BairroDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Bairro bairro = modelMapper.map(dto, Bairro.class);
        if (dto.getIdCidade() != null) {
            Optional<Cidade> cidade = cidadeService.getCidadeById(dto.getIdCidade());
            if (!cidade.isPresent()) {
                bairro.setCidade(null);
            } else {
                bairro.setCidade(cidade.get());
            }
        }
        return bairro;
    }

}
