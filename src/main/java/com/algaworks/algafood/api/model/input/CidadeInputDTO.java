package com.algaworks.algafood.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    @Valid
    private EstadoIdInputDTO estado;
}
