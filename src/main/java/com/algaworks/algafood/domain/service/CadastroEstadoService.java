package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    public Estado buscar(Long estadoId) {
        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if (estadoAtual == null ) {
            throw new EntidadeNaoEncontradaException(
                String.format("Não há nenhum cadastro de estado de código %d", estadoId));
        }

        return estadoAtual;
    }

    public Estado adicionar(Estado estado) {
        return estadoRepository.adicionar(estado);
    }

    public Estado atualizar(Long estadoId, Estado estado) {
        Estado estadoAtual = estadoRepository.buscar(estadoId);

        if (estadoAtual == null) {
            throw new EntidadeNaoEncontradaException(
                String.format("Não há nenhum cadastro de estado de código %d", estadoId));
        }

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return estadoRepository.adicionar(estadoAtual);
    }

    public void remover(Long estadoId) {
        try {
            estadoRepository.remover(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                String.format("Não há nenhum cadastro de estado de código %d", estadoId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                String.format("A entidade de código %d não pode ser excluida , pois está em uso", estadoId));
        }
        
    }
}