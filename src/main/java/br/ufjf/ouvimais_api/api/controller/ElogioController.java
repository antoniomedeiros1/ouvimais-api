package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.ElogioDTO;
import br.ufjf.ouvimais_api.model.entity.Elogio;
import br.ufjf.ouvimais_api.service.ElogioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/elogios")
@CrossOrigin
public class ElogioController {

    private final ElogioService service;

    public ElogioController(ElogioService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Elogio> elogios = service.getElogios();
        return ResponseEntity.ok(elogios.stream().map(ElogioDTO::create).collect(Collectors.toList()));
    }

}
