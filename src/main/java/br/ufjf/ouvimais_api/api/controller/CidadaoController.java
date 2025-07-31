package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadaoDTO;
import br.ufjf.ouvimais_api.api.dto.CidadaoDTO;
import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.model.entity.Cidadao;
import br.ufjf.ouvimais_api.model.entity.Cidadao;
import br.ufjf.ouvimais_api.model.entity.Cidade;
import br.ufjf.ouvimais_api.model.entity.Endereco;
import br.ufjf.ouvimais_api.service.CidadaoService;
import br.ufjf.ouvimais_api.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final EnderecoService enderecoService;

    public CidadaoController(
        CidadaoService service,
        EnderecoService enderecoService
    ) {
        this.service = service;
        this.enderecoService = enderecoService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Cidadao> cidadaos = service.getCidadaos();
        return ResponseEntity.ok(cidadaos.stream().map(CidadaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Cidadao> cidadao = service.getCidadaoById(id);
        if (!cidadao.isPresent()){
            return new ResponseEntity("Cidadao nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cidadao.map(CidadaoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CidadaoDTO dto) {
        try {
            Cidadao cidadao = convert(dto);
            cidadao = service.save(cidadao);
            return new ResponseEntity(cidadao, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody CidadaoDTO dto) {
        if (!service.getCidadaoById(id).isPresent()) {
            return new ResponseEntity("Cidadao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Cidadao cidadao = convert(dto);
            cidadao.setId(id);
            service.save(cidadao);
            return ResponseEntity.ok(cidadao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Cidadao> cidadao = service.getCidadaoById(id);
        if (!cidadao.isPresent()) {
            return new ResponseEntity("Cidadao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.delete(cidadao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Cidadao convert(CidadaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Cidadao cidadao = modelMapper.map(dto, Cidadao.class);
        if (dto.getIdEndereco() != null) {
            Optional<Endereco> endereco = enderecoService.getEnderecoById(dto.getIdEndereco());
            if (!endereco.isPresent()) {
                cidadao.setEndereco(null);
            } else {
                cidadao.setEndereco(endereco.get());
            }
        }
        return cidadao;
    }

}
