package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.DenunciaDTO;
import br.ufjf.ouvimais_api.api.dto.DenunciaDTO;
import br.ufjf.ouvimais_api.api.dto.DenunciaDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Denuncia;
import br.ufjf.ouvimais_api.model.entity.Denuncia;
import br.ufjf.ouvimais_api.service.CidadaoService;
import br.ufjf.ouvimais_api.service.DenunciaService;
import br.ufjf.ouvimais_api.service.SetorService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/denuncias")
@CrossOrigin
public class DenunciaController {

    private final DenunciaService service;
    private final CidadaoService cidadaoService;
    private final SetorService setorService;

    public DenunciaController(
        DenunciaService service,
        CidadaoService cidadaoService,
        SetorService setorService
    ) {
        this.service = service;
        this.cidadaoService = cidadaoService;
        this.setorService = setorService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Denuncia> denuncias = service.getDenuncias();
        return ResponseEntity.ok(denuncias.stream().map(DenunciaDTO::create).collect(Collectors.toList()));
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Denuncia> denuncia = service.getDenunciaById(id);
        if (!denuncia.isPresent()){
            return new ResponseEntity("Denuncia nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(denuncia.map(DenunciaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody DenunciaDTO dto) {
        try {
            Denuncia denuncia = convert(dto);
            denuncia = service.save(denuncia);
            return new ResponseEntity(denuncia, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Denuncia convert(DenunciaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Denuncia denuncia = modelMapper.map(dto, Denuncia.class);
        if (dto.getIdCidadao() != null) {
            Optional<Cidadao> cidadao = cidadaoService.getCidadaoById(dto.getIdCidadao());
            if (!cidadao.isPresent()) {
                denuncia.setCidadao(null);
            } else {
                denuncia.setCidadao(cidadao.get());
            }
        }
        if (dto.getIdSetor() != null) {
            Optional<Setor> setor = setorService.getSetorById(dto.getIdSetor());
            if (!setor.isPresent()) {
                denuncia.setSetor(null);
            } else {
                denuncia.setSetor(setor.get());
            }
        }
        return denuncia;
    }

}
