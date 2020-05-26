package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
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

    private static final String MSG_CIDADE_EM_USO = "A cidade de código %d não pode ser excluida, pois está em uso";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade buscar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
            .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));

    }

    @Transactional
    public Cidade cadastrar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = null;

        try {
            estado = cadastroEstadoService.buscar(estadoId);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(Long cidadeId, Cidade cidade) {
        Cidade cidadeAtual = buscar(cidadeId);

        Long estadoId = cidade.getEstado().getId();
        Estado estado = null;

        try {
            estado = cadastroEstadoService.buscar(estadoId);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }

        cidade.setEstado(estado);

        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        return cidadeRepository.save(cidadeAtual);
    }

    @Transactional
    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
            cidadeRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }
}
