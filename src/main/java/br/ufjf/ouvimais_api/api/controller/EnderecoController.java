package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.EnderecoDTO;
import br.ufjf.ouvimais_api.api.dto.EnderecoDTO;
import br.ufjf.ouvimais_api.model.entity.Endereco;
import br.ufjf.ouvimais_api.model.entity.Endereco;
import br.ufjf.ouvimais_api.service.EnderecoService;
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

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Endereco> enderecos = service.getEnderecos();
        return ResponseEntity.ok(enderecos.stream().map(EnderecoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Endereco> endereco = service.getEnderecoById(id);
        if (!endereco.isPresent()){
            return new ResponseEntity("Endereco nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(endereco.map(EnderecoDTO::create));
    }

}
