package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.EnderecoDTO;
import br.ufjf.ouvimais_api.api.dto.EnderecoDTO;
import br.ufjf.ouvimais_api.api.dto.EnderecoDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Endereco;
import br.ufjf.ouvimais_api.model.entity.Endereco;
import br.ufjf.ouvimais_api.service.BairroService;
import br.ufjf.ouvimais_api.service.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enderecos")
@CrossOrigin
public class EnderecoController {

    private final EnderecoService service;
    private final BairroService bairroService;

    public EnderecoController(
            EnderecoService service,
            BairroService bairroService
    ) {
        this.service = service;
        this.bairroService = bairroService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Endereco> enderecos = service.getEnderecos();
        return ResponseEntity.ok(enderecos.stream().map(EnderecoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Endereco> endereco = service.getEnderecoById(id);
        if (!endereco.isPresent()){
            return new ResponseEntity("Endereco nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(endereco.map(EnderecoDTO::create));
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EnderecoDTO dto) {
        if (!service.getEnderecoById(id).isPresent()) {
            return new ResponseEntity("Endereco não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Endereco endereco = convert(dto);
            endereco.setId(id);
            service.save(endereco);
            return ResponseEntity.ok(endereco);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody EnderecoDTO dto) {
        try {
            Endereco endereco = convert(dto);
            endereco = service.save(endereco);
            return new ResponseEntity(endereco, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Endereco convert(EnderecoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        if (dto.getIdBairro() != null) {
            Optional<Bairro> bairro = bairroService.getBairroById(dto.getIdBairro());
            if (!bairro.isPresent()) {
                endereco.setBairro(null);
            } else {
                endereco.setBairro(bairro.get());
            }
        }
        return endereco;
    }

}
