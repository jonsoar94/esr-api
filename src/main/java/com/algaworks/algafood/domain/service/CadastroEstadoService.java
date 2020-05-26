package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroEstadoService {

    public static final String MSG_ESTADO_EM_USO = "A entidade de código %d não pode ser excluida , pois está em uso";

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado buscar(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }

    @Transactional
    public Estado adicionar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long estadoId, Estado estado) {
        Estado estadoAtual = buscar(estadoId);

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return estadoRepository.save(estadoAtual);
    }

    public void remover(Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("A entidade de código %d não pode ser excluida , pois está em uso", estadoId));
        }
    }
}
