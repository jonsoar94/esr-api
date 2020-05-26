package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.assembler.CidadeConverter;
import com.algaworks.algafood.api.model.CidadeDTO;
import com.algaworks.algafood.api.model.input.CidadeInputDTO;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cadastroCidadeService;

    @Autowired
    private CidadeConverter cidadeConverter;

    @GetMapping
    public List<CidadeDTO> listar() {
        List<Cidade> cidades = cadastroCidadeService.listar();
        return cidadeConverter.toCollectionDTO(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        Cidade cidade = cadastroCidadeService.buscar(cidadeId);
        return cidadeConverter.toDto(cidade);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeDTO adicionar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO) {
        Cidade cidade = cidadeConverter.toDomain(cidadeInputDTO);
        cidade = cadastroCidadeService.cadastrar(cidade);
        return cidadeConverter.toDto(cidade);
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@PathVariable Long cidadeId, @RequestBody CidadeInputDTO cidadeInputDTO) {
        Cidade cidade = cidadeConverter.toDomain(cidadeInputDTO);
        cidade = cadastroCidadeService.atualizar(cidadeId, cidade);
        return cidadeConverter.toDto(cidade);
    }

    @DeleteMapping("/{cidadeId}")
    public void remover(@PathVariable Long cidadeId) {
        cadastroCidadeService.excluir(cidadeId);
    }
}
