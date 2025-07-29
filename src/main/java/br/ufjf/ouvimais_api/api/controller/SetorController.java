package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.SetorDTO;
import br.ufjf.ouvimais_api.model.entity.Setor;
import br.ufjf.ouvimais_api.service.SetorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/setores")
@CrossOrigin
public class SetorController {

    private final SetorService service;

    public SetorController(SetorService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Setor> setores = service.getSetors();
        return ResponseEntity.ok(setores.stream().map(SetorDTO::create).collect(Collectors.toList()));
    }

}
