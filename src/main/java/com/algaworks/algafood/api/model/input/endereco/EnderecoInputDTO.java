package com.algaworks.algafood.api.model.input.endereco;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.api.model.input.cidade.CidadeIdInputDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoInputDTO {

	@NotBlank
	private String cep;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String bairro;
	
	@Valid
	@NotNull
	private CidadeIdInputDTO cidade;
}
