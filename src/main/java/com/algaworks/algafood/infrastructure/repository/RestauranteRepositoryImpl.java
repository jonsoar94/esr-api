package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);

        // from Restaurante
        Root<Restaurante> root = criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (nome != null) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaFreteInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (taxaFreteFinal != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
        }

        // where 
        criteria.where(predicates.toArray(new Predicate[0]));


        TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findRestauranteComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
    }

    // @Override
    // public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        
    //     var jpql = new StringBuilder();

    //     jpql.append("from Restaurante where 0 = 0");

    //     var parametros = new HashMap<String, Object>();

    //     if (nome != null) {
    //         jpql.append("and nome like :nome ");
    //         parametros.put("nome", "%" + nome + "%");
    //     }

    //     if (taxaFreteInicial != null ) {
    //         jpql.append("and taxaFrete >= :taxaFreteInicial ");
    //         parametros.put("taxaFreteInicial", taxaFreteInicial);
    //     }

    //     if (taxaFreteFinal != null) {
    //         jpql.append("and taxaFrete <= :taxaFreteFinal");
    //         parametros.put("taxaFreteFinal", taxaFreteFinal);
    //     }

    //     TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
        
    //     parametros.forEach((valorChave, valorPropriedade) -> query.setParameter(valorChave, valorPropriedade));

    //     return query.getResultList();
    // }
}