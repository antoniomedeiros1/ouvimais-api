package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.FuncionarioDTO;
import br.ufjf.ouvimais_api.model.entity.Funcionario;
import br.ufjf.ouvimais_api.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

}
