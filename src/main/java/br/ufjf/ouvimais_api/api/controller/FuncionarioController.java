package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.FuncionarioDTO;
import br.ufjf.ouvimais_api.api.dto.FuncionarioDTO;
import br.ufjf.ouvimais_api.api.dto.FuncionarioDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Funcionario;
import br.ufjf.ouvimais_api.model.entity.Funcionario;
import br.ufjf.ouvimais_api.service.FuncionarioService;
import br.ufjf.ouvimais_api.service.InstituicaoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/funcionarios")
@CrossOrigin
public class FuncionarioController {

    private final FuncionarioService service;
    private final InstituicaoService instituicaoService;

    public FuncionarioController(
            FuncionarioService service,
            InstituicaoService instituicaoService
    ) {
        this.service = service;
        this.instituicaoService = instituicaoService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Funcionario> funcionarios = service.getFuncionarios();
        return ResponseEntity.ok(funcionarios.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (!funcionario.isPresent()){
            return new ResponseEntity("Funcionario nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody FuncionarioDTO dto) {
        try {
            Funcionario funcionario = convert(dto);
            funcionario = service.save(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody FuncionarioDTO dto) {
        if (!service.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Funcionario funcionario = convert(dto);
            funcionario.setId(id);
            service.save(funcionario);
            return ResponseEntity.ok(funcionario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Funcionario convert(FuncionarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        if (dto.getIdInstituicao() != null) {
            Optional<Instituicao> instituicao = instituicaoService.getInstituicaoById(dto.getIdInstituicao());
            if (!instituicao.isPresent()) {
                funcionario.setInstituicao(null);
            } else {
                funcionario.setInstituicao(instituicao.get());
            }
        }
        return funcionario;
    }

}
