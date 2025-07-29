package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.ElogioDTO;
import br.ufjf.ouvimais_api.api.dto.ElogioDTO;
import br.ufjf.ouvimais_api.model.entity.Elogio;
import br.ufjf.ouvimais_api.model.entity.Elogio;
import br.ufjf.ouvimais_api.service.ElogioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Elogio> elogio = service.getElogioById(id);
        if (!elogio.isPresent()){
            return new ResponseEntity("Elogio nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(elogio.map(ElogioDTO::create));
    }

}
