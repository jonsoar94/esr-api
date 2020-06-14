package com.algaworks.algafood.api.model.input.cozinha;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaInputDTO {

    @NotBlank
    private String nome;

}
