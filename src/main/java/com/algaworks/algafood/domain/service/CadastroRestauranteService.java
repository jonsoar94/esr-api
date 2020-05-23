package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoExcontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoExcontradoException(restauranteId));
    }

    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = null;
        
        try {
            cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }
        // se o restaurante existir, inserir ele ao objeto restaurante
        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteAtual = buscar(restauranteId);

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = null;
        
        try {
            cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e.getCause());
        }

        restaurante.setCozinha(cozinha);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
                "dataCadastro");

        return restauranteRepository.save(restauranteAtual);
    }
}