package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    private static final String COZINHA_MSG = "Não há nenhuma cozinha cadastrada de código %d";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(Long id) {
        return restauranteRepository.findById(id);
    } 

    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(COZINHA_MSG, cozinhaId)));

        // se o restaurante existir, inserir ele ao objeto restaurante
        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

        if(!restauranteAtual.isPresent()) {
            return null;
        }

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> 
            new EntidadeNaoEncontradaException(String.format(COZINHA_MSG, cozinhaId)));

        restaurante.setCozinha(cozinha);
        BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
        
        return restauranteRepository.save(restauranteAtual.get());
    }
}