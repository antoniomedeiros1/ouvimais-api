package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.DenunciaDTO;
import br.ufjf.ouvimais_api.model.entity.Denuncia;
import br.ufjf.ouvimais_api.service.DenunciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/denuncias")
@CrossOrigin
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Denuncia> denuncias = service.getDenuncias();
        return ResponseEntity.ok(denuncias.stream().map(DenunciaDTO::create).collect(Collectors.toList()));
    }

}
