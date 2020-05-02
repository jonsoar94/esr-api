package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> listar() {
        return restauranteRepository.listar();
    }

    public Restaurante buscar(Long id) {
        return restauranteRepository.buscar(id);
    } 
}