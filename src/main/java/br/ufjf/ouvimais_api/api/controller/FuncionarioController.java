package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.FuncionarioDTO;
import br.ufjf.ouvimais_api.api.dto.FuncionarioDTO;
import br.ufjf.ouvimais_api.model.entity.Funcionario;
import br.ufjf.ouvimais_api.model.entity.Funcionario;
import br.ufjf.ouvimais_api.service.FuncionarioService;
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

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Funcionario> funcionarios = service.getFuncionarios();
        return ResponseEntity.ok(funcionarios.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        if (!funcionario.isPresent()){
            return new ResponseEntity("Funcionario nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(funcionario.map(FuncionarioDTO::create));
    }

}
