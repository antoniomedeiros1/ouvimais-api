package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.EnderecoDTO;
import br.ufjf.ouvimais_api.model.entity.Endereco;
import br.ufjf.ouvimais_api.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enderecos")
@CrossOrigin
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Endereco> enderecos = service.getEnderecos();
        return ResponseEntity.ok(enderecos.stream().map(EnderecoDTO::create).collect(Collectors.toList()));
    }

}
