package com.algaworks.algafood.api.model.input.cidade;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIdInputDTO {

	@NotNull
	private Long id;
}
