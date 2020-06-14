package com.algaworks.algafood.api.model.input.estado;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoIdInputDTO {

    @NotNull
    private Long id;
}
