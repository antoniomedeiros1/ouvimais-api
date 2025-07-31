package br.ufjf.ouvimais_api.api.controller;

import br.ufjf.ouvimais_api.api.dto.CidadeDTO;
import br.ufjf.ouvimais_api.api.dto.ReclamacaoDTO;
import br.ufjf.ouvimais_api.api.dto.ReclamacaoDTO;
import br.ufjf.ouvimais_api.api.dto.ReclamacaoDTO;
import br.ufjf.ouvimais_api.model.entity.*;
import br.ufjf.ouvimais_api.model.entity.Reclamacao;
import br.ufjf.ouvimais_api.service.CidadaoService;
import br.ufjf.ouvimais_api.service.EnderecoService;
import br.ufjf.ouvimais_api.service.SetorService;
import br.ufjf.ouvimais_api.service.ReclamacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/reclamacoes")
@CrossOrigin
public class ReclamacaoController {

    private final ReclamacaoService service;
    private final CidadaoService cidadaoService;
    private final SetorService setorService;
    private final EnderecoService enderecoService;

    public ReclamacaoController(
            ReclamacaoService service,
            CidadaoService cidadaoService,
            SetorService setorService,
            EnderecoService enderecoService
    ) {
        this.service = service;
        this.cidadaoService = cidadaoService;
        this.setorService = setorService;
        this.enderecoService = enderecoService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Reclamacao> reclamacoes = service.getReclamacaos();
        return ResponseEntity.ok(reclamacoes.stream().map(ReclamacaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Reclamacao> reclamacao = service.getReclamacaoById(id);
        if (!reclamacao.isPresent()){
            return new ResponseEntity("Reclamacao nao encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(reclamacao.map(ReclamacaoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ReclamacaoDTO dto) {
        try {
            Reclamacao reclamacao = convert(dto);
            reclamacao = service.save(reclamacao);
            return new ResponseEntity(reclamacao, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody ReclamacaoDTO dto) {
        if (!service.getReclamacaoById(id).isPresent()) {
            return new ResponseEntity("Reclamacao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Reclamacao reclamacao = convert(dto);
            reclamacao.setId(id);
            service.save(reclamacao);
            return ResponseEntity.ok(reclamacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<Reclamacao> reclamacao = service.getReclamacaoById(id);
        if (!reclamacao.isPresent()) {
            return new ResponseEntity("Reclamacao não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.delete(reclamacao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Reclamacao convert(ReclamacaoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Reclamacao reclamacao = modelMapper.map(dto, Reclamacao.class);
        if (dto.getIdCidadao() != null) {
            Optional<Cidadao> cidadao = cidadaoService.getCidadaoById(dto.getIdCidadao());
            if (!cidadao.isPresent()) {
                reclamacao.setCidadao(null);
            } else {
                reclamacao.setCidadao(cidadao.get());
            }
        }
        if (dto.getIdSetor() != null) {
            Optional<Setor> setor = setorService.getSetorById(dto.getIdSetor());
            if (!setor.isPresent()) {
                reclamacao.setSetor(null);
            } else {
                reclamacao.setSetor(setor.get());
            }
        }
        if (dto.getIdEndereco() != null) {
            Optional<Endereco> endereco = enderecoService.getEnderecoById(dto.getIdEndereco());
            if (!endereco.isPresent()) {
                reclamacao.setEndereco(null);
            } else {
                reclamacao.setEndereco(endereco.get());
            }
        }
        return reclamacao;
    }

}
