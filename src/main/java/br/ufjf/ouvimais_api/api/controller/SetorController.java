package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.SetorDTO;
import br.ufjf.ouvimais_api.api.dto.SetorDTO;
import br.ufjf.ouvimais_api.api.dto.SetorDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Setor;
import br.ufjf.ouvimais_api.model.entity.Setor;
import br.ufjf.ouvimais_api.service.InstituicaoService;
import br.ufjf.ouvimais_api.service.SetorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/setores")
@CrossOrigin
public class SetorController {

    private final SetorService service;
    private final InstituicaoService instituicaoService;

    public SetorController(
            SetorService service,
            InstituicaoService instituicaoService
    ) {
        this.service = service;
        this.instituicaoService = instituicaoService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Setor> setores = service.getSetors();
        return ResponseEntity.ok(setores.stream().map(SetorDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Setor> setor = service.getSetorById(id);
        if (!setor.isPresent()){
            return new ResponseEntity("Setor nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(setor.map(SetorDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody SetorDTO dto) {
        try {
            Setor setor = convert(dto);
            setor = service.save(setor);
            return new ResponseEntity(setor, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody SetorDTO dto) {
        if (!service.getSetorById(id).isPresent()) {
            return new ResponseEntity("Setor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Setor setor = convert(dto);
            setor.setId(id);
            service.save(setor);
            return ResponseEntity.ok(setor);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Setor convert(SetorDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Setor setor = modelMapper.map(dto, Setor.class);
        if (dto.getIdInstituicao() != null) {
            Optional<Instituicao> instituicao = instituicaoService.getInstituicaoById(dto.getIdInstituicao());
            if (!instituicao.isPresent()) {
                setor.setInstituicao(null);
            } else {
                setor.setInstituicao(instituicao.get());
            }
        }
        return setor;
    }

}
