package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesterController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cozinhas/nome")
    public List<Cozinha> listaCozinhasPorNome(@RequestParam String nome) {
        return cozinhaRepository.findTodasByNomeContaining(nome);
    }

    @GetMapping("/cozinhas/unica-por-nome")
    public Optional<Cozinha> unicaPorNome(@RequestParam String nome) {
        return cozinhaRepository.findByNome(nome);
    }

    @GetMapping("/cozinhas/exists")
    public boolean existsByNome(@RequestParam String nome) {
        return cozinhaRepository.existsByNome(nome);
    }

    @GetMapping("/restaurantes/busca-por-taxafrete")
    public List<Restaurante> buscaPorTaxaFrete(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("/restaurantes/busca-por-nome")
    public List<Restaurante> buscarPorNome(@RequestParam String nome, @RequestParam Long cozinhaId) {
        return restauranteRepository.consultarPorNome(nome, cozinhaId);
    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> buscaPrimeiroPorNome(@RequestParam String nome) {
        return restauranteRepository.findFirstByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/top-2-por-nome")
    public List<Restaurante> buscaTop2PorNome(@RequestParam String nome) {
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    @GetMapping("/restaurantes/count")
    public int countByCozinhaId(@RequestParam Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }

    @GetMapping("/restaurante/find")
    public List<Restaurante> findRestaurantes(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.find(nome, taxaInicial, taxaFinal);
    }
}