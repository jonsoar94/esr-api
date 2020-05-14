package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    private static final String CIDADE_MSG = "Não há nenhuma cidade cadastrada de código %d";
    private static final String ESTADO_NAO_EXISTENTE_MSG = "Não há nenhum estado cadastrado de código %d";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(CIDADE_MSG, cidadeId)));

    }

    public Cidade cadastrar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstadoService.buscar(estadoId);

        if (estado == null) {
            throw new NegocioException(String.format(ESTADO_NAO_EXISTENTE_MSG, estadoId));
        }

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtual = buscar(cidadeId);

        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstadoService.buscar(estadoId);

        if (estado == null) {
            throw new NegocioException(String.format(ESTADO_NAO_EXISTENTE_MSG, estadoId));
        }

        cidade.setEstado(estado);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return cidadeRepository.save(cidadeAtual);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                String.format(CIDADE_MSG, cidadeId));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("A cidade de código %d não pode ser excluida, pois está em uso", cidadeId));
        }
    }

}