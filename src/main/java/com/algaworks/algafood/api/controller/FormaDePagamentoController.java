package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.algaworks.algafood.api.assembler.FormaDePagamentoConverter;
import com.algaworks.algafood.api.model.FormaDePagamentoDTO;
import com.algaworks.algafood.api.model.FormaDePagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaDePagamento;
import com.algaworks.algafood.domain.service.CadastroFormaDePagamentoService;

@RestController
@RequestMapping("formas-pagamento")
public class FormaDePagamentoController {
	
	@Autowired
	private CadastroFormaDePagamentoService cadastroFormaDePagamentoService;
	
	@Autowired
	private FormaDePagamentoConverter formaDePagamentoConverter;
	
	@GetMapping
	public List<FormaDePagamentoDTO> listar() {
		List<FormaDePagamento> formasPagamento = cadastroFormaDePagamentoService.listar();
		return formaDePagamentoConverter.toCollectionDTO(formasPagamento);
	}
	
	@GetMapping("/{formaDePagamentoId}")
	public FormaDePagamentoDTO buscar(@PathVariable Long formaDePagamentoId) {
		return formaDePagamentoConverter.toDto(cadastroFormaDePagamentoService.buscar(formaDePagamentoId));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaDePagamentoDTO salvar(@RequestBody @Valid FormaDePagamentoInputDTO formaDePagamentoInputDTO) {
		FormaDePagamento formaDePagamento = formaDePagamentoConverter.toDomain(formaDePagamentoInputDTO);
		formaDePagamento = cadastroFormaDePagamentoService.salvar(formaDePagamento);
		
		return formaDePagamentoConverter.toDto(formaDePagamento);
	}
	
	@PutMapping("/{formaDePagamentoId}")
	public FormaDePagamentoDTO salvar(@PathVariable Long formaDePagamentoId, @RequestBody @Valid FormaDePagamentoInputDTO formaDePagamentoInputDTO) {
		FormaDePagamento formaDePagamento = formaDePagamentoConverter.toDomain(formaDePagamentoInputDTO);
		formaDePagamento = cadastroFormaDePagamentoService.atualizar(formaDePagamentoId, formaDePagamento);
		return formaDePagamentoConverter.toDto(formaDePagamento);
	}
	
	@DeleteMapping("/{formaDePagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long formaDePagamentoId) {
		cadastroFormaDePagamentoService.remover(formaDePagamentoId);
	}
}
