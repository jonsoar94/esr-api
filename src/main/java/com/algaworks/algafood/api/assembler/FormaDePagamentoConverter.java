package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.FormaDePagamentoDTO;
import com.algaworks.algafood.api.model.FormaDePagamentoInputDTO;
import com.algaworks.algafood.domain.model.FormaDePagamento;

@Component
public class FormaDePagamentoConverter implements Converter<FormaDePagamento, FormaDePagamentoDTO, FormaDePagamentoInputDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public FormaDePagamento toDomain(FormaDePagamentoInputDTO inputDto) {
		return modelMapper.map(inputDto, FormaDePagamento.class);
	}

	@Override
	public FormaDePagamentoDTO toDto(FormaDePagamento domain) {
		return modelMapper.map(domain, FormaDePagamentoDTO.class);
	}

	@Override
	public List<FormaDePagamentoDTO> toCollectionDTO(List<FormaDePagamento> list) {
		return list.stream().map(formaDePagamento -> toDto(formaDePagamento)).collect(Collectors.toList());
	}

	
}
