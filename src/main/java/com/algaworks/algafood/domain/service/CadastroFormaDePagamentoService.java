package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.FormaDePagamentoNaoEncontradaException;
import com.algaworks.algafood.domain.model.FormaDePagamento;
import com.algaworks.algafood.domain.repository.FormaDePagamentoRepository;

@Service
public class CadastroFormaDePagamentoService {
	
	@Autowired
	private FormaDePagamentoRepository formaDePagamentoRepository;
	
	public List<FormaDePagamento> listar() {
		return formaDePagamentoRepository.findAll();
	}
	
	public FormaDePagamento salvar(FormaDePagamento formaDePagamento) {
		return formaDePagamentoRepository.save(formaDePagamento);
	}
	
	public FormaDePagamento buscar(Long formaDePagamentoId) {
		return formaDePagamentoRepository.findById(formaDePagamentoId)
				.orElseThrow(() -> new FormaDePagamentoNaoEncontradaException(formaDePagamentoId));
	}
	

	@Transactional
	public FormaDePagamento atualizar(Long formaDePagamentoId, FormaDePagamento formaDePagamento) {
		FormaDePagamento formaDePagamentoAtual = buscar(formaDePagamentoId);
		
		BeanUtils.copyProperties(formaDePagamento, formaDePagamentoAtual, "id");
		
		return formaDePagamentoRepository.save(formaDePagamentoAtual);
	}
	
	@Transactional
	public void remover(Long formaDePagamentoId) {
		try {
			formaDePagamentoRepository.deleteById(formaDePagamentoId);
			formaDePagamentoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new FormaDePagamentoNaoEncontradaException(formaDePagamentoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("A entidade de código %d não pode ser excluida, pois está em uso.", formaDePagamentoId));
		}
	}
}
