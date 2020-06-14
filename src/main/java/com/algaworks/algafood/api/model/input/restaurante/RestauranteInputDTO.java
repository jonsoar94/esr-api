package com.algaworks.algafood.api.model.input.restaurante;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.algaworks.algafood.api.model.input.cozinha.CozinhaIdInputDTO;
import com.algaworks.algafood.api.model.input.endereco.EnderecoInputDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @Valid
    @NotNull
    private CozinhaIdInputDTO cozinha;
    
    @Valid
    @NotNull
    private EnderecoInputDTO endereco;
}
