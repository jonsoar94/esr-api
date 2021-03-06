package com.algaworks.algafood.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormaDePagamentoInputDTO {

	@NotBlank
	private String descricao;
}
