package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    private static final String CIDADE_MSG = "Não há nenhuma cidade cadastrada de código %d";
    private static final String ESTADO_MSG = "Não há nenhum estado cadastrado de código %d";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    public Cidade buscar(Long cidadeId) {
        Cidade cidade = cidadeRepository.buscar(cidadeId);

        if (cidade == null) {
            throw new EntidadeNaoEncontradaException(
                String.format(CIDADE_MSG, cidadeId));
        }

        return cidade;
    }

    public Cidade cadastrar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(estadoId);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                String.format(ESTADO_MSG, estadoId));
        }

        cidade.setEstado(estado);

        return cidadeRepository.adicionar(cidade);
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

        if (cidadeAtual == null) {
            return null;
        }

        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(estadoId);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(
                String.format(ESTADO_MSG, estadoId));
        }

        cidade.setEstado(estado);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return cidadeRepository.adicionar(cidadeAtual);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.remover(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                String.format(CIDADE_MSG, cidadeId));
        }
    }

}