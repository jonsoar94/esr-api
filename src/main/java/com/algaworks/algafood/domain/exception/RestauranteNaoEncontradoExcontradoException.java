package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoExcontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public RestauranteNaoEncontradoExcontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoExcontradoException(Long restauranteId) {
        this(String.format("Não há cadastro de restaurante de código %d", restauranteId));
    }
}