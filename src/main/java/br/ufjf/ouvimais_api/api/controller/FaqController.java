package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.FaqDTO;
import br.ufjf.ouvimais_api.api.dto.FaqDTO;
import br.ufjf.ouvimais_api.api.dto.FaqDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Faq;
import br.ufjf.ouvimais_api.model.entity.Faq;
import br.ufjf.ouvimais_api.service.InstituicaoService;
import br.ufjf.ouvimais_api.service.FaqService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/faqs")
@CrossOrigin
public class FaqController {

    private final FaqService service;
    private final InstituicaoService instituicaoService;

    public FaqController(
            FaqService service,
            InstituicaoService instituicaoService
    ) {
        this.service = service;
        this.instituicaoService = instituicaoService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Faq> faqs = service.getFaqs();
        return ResponseEntity.ok(faqs.stream().map(FaqDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Faq> faq = service.getFaqById(id);
        if (!faq.isPresent()){
            return new ResponseEntity("Faq nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(faq.map(FaqDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody FaqDTO dto) {
        try {
            Faq faq = convert(dto);
            faq = service.save(faq);
            return new ResponseEntity(faq, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody FaqDTO dto) {
        if (!service.getFaqById(id).isPresent()) {
            return new ResponseEntity("Faq não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Faq faq = convert(dto);
            faq.setId(id);
            service.save(faq);
            return ResponseEntity.ok(faq);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Faq> faq = service.getFaqById(id);
        if (!faq.isPresent()) {
            return new ResponseEntity("Faq não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.delete(faq.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Faq convert(FaqDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Faq faq = modelMapper.map(dto, Faq.class);
        if (dto.getIdInstituicao() != null) {
            Optional<Instituicao> instituicao = instituicaoService.getInstituicaoById(dto.getIdInstituicao());
            if (!instituicao.isPresent()) {
                faq.setInstituicao(null);
            } else {
                faq.setInstituicao(instituicao.get());
            }
        }
        return faq;
    }

}
