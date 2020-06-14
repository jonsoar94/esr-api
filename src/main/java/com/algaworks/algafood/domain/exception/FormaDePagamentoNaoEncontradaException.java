package com.algaworks.algafood.domain.exception;

public class FormaDePagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public FormaDePagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FormaDePagamentoNaoEncontradaException(Long formaDePagamentoId) {
        this(String.format("Não existe cadastro de forma de pagamento de código %d", formaDePagamentoId));
    }
}