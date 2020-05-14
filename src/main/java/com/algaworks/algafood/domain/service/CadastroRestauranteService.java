package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    private static final String RESTAURANTE_NAO_ENCONTRADO_MSG = "Não há nenhum restaurante cadastrado de código %d";
    private static final String COZINHA_NAO_ENCONTRADA_MSG = "Não há nenhuma cozinha cadastrada de código %d";

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(RESTAURANTE_NAO_ENCONTRADO_MSG));
    }

    public Restaurante adicionar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        if(cozinha == null) {
            throw new NegocioException(String.format(COZINHA_NAO_ENCONTRADA_MSG, cozinhaId));
        }
        // se o restaurante existir, inserir ele ao objeto restaurante
        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante atualizar(Long restauranteId, Restaurante restaurante) {
        Restaurante restauranteAtual = buscar(restauranteId);

        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

        if (cozinha == null) {
            throw new NegocioException(String.format(COZINHA_NAO_ENCONTRADA_MSG, cozinhaId));
        }

        restaurante.setCozinha(cozinha);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
                "dataCadastro");

        return restauranteRepository.save(restauranteAtual);
    }
}