package com.algaworks.algafood.api.model.input.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputComSenhaDTO extends UsuarioInputDTO {

	@NotBlank
	private String senha;
}
