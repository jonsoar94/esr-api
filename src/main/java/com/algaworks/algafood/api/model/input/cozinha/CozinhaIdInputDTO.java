package com.algaworks.algafood.api.model.input.cozinha;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaIdInputDTO {

    @NotNull
    private Long id;
}
