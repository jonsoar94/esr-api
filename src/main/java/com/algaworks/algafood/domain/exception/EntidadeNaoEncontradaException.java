package com.algaworks.algafood.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    private static final long serialVersionUID = 1L;

    

}