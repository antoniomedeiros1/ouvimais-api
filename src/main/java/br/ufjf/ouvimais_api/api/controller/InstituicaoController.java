package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.InstituicaoDTO;
import br.ufjf.ouvimais_api.api.dto.InstituicaoDTO;
import br.ufjf.ouvimais_api.api.dto.InstituicaoDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Instituicao;
import br.ufjf.ouvimais_api.model.entity.Instituicao;
import br.ufjf.ouvimais_api.service.EnderecoService;
import br.ufjf.ouvimais_api.service.InstituicaoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/instituicoes")
@CrossOrigin
public class InstituicaoController {

    private final InstituicaoService service;
    private final EnderecoService enderecoService;

    public InstituicaoController(
            InstituicaoService service,
            EnderecoService enderecoService
    ) {
        this.service = service;
        this.enderecoService = enderecoService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Instituicao> instituicoes = service.getInstituicaos();
        return ResponseEntity.ok(instituicoes.stream().map(InstituicaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Instituicao> instituicao = service.getInstituicaoById(id);
        if (!instituicao.isPresent()){
            return new ResponseEntity("Instituicao nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(instituicao.map(InstituicaoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody InstituicaoDTO dto) {
        try {
            Instituicao instituicao = convert(dto);
            instituicao = service.save(instituicao);
            return new ResponseEntity(instituicao, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody InstituicaoDTO dto) {
        if (!service.getInstituicaoById(id).isPresent()) {
            return new ResponseEntity("Instituicao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Instituicao instituicao = convert(dto);
            instituicao.setId(id);
            service.save(instituicao);
            return ResponseEntity.ok(instituicao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Instituicao convert(InstituicaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Instituicao instituicao = modelMapper.map(dto, Instituicao.class);
        if (dto.getIdEndereco() != null) {
            Optional<Endereco> endereco = enderecoService.getEnderecoById(dto.getIdEndereco());
            if (!endereco.isPresent()) {
                instituicao.setEndereco(null);
            } else {
                instituicao.setEndereco(endereco.get());
            }
        }
        return instituicao;
    }

}
