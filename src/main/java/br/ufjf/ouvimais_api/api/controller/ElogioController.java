package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.ElogioDTO;
import br.ufjf.ouvimais_api.api.dto.ElogioDTO;
import br.ufjf.ouvimais_api.api.dto.ElogioDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Elogio;
import br.ufjf.ouvimais_api.service.CidadaoService;
import br.ufjf.ouvimais_api.service.ElogioService;
import br.ufjf.ouvimais_api.service.SetorService;
import org.modelmapper.ModelMapper;
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
    private final CidadaoService cidadaoService;
    private final SetorService setorService;

    public ElogioController(
            ElogioService service,
            CidadaoService cidadaoService,
            SetorService setorService
    ) {
        this.service = service;
        this.cidadaoService = cidadaoService;
        this.setorService = setorService;
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

    @PostMapping()
    public ResponseEntity post(@RequestBody ElogioDTO dto) {
        try {
            Elogio elogio = convert(dto);
            elogio = service.save(elogio);
            return new ResponseEntity(elogio, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Elogio convert(ElogioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Elogio elogio = modelMapper.map(dto, Elogio.class);
        if (dto.getIdCidadao() != null) {
            Optional<Cidadao> cidadao = cidadaoService.getCidadaoById(dto.getIdCidadao());
            if (!cidadao.isPresent()) {
                elogio.setCidadao(null);
            } else {
                elogio.setCidadao(cidadao.get());
            }
        }
        if (dto.getIdSetor() != null) {
            Optional<Setor> setor = setorService.getSetorById(dto.getIdSetor());
            if (!setor.isPresent()) {
                elogio.setSetor(null);
            } else {
                elogio.setSetor(setor.get());
            }
        }
        return elogio;
    }

}
