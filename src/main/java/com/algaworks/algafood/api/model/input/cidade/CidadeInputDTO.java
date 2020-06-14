package com.algaworks.algafood.api.model.input.cidade;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.api.model.input.estado.EstadoIdInputDTO;

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
