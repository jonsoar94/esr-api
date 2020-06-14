package com.algaworks.algafood.api.model.input.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SenhaInputDTO {

    @NotBlank
	private String senhaAtual;

    @NotBlank
	private String novaSenha;
}
