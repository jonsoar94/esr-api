package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.RestauranteDTO;
import com.algaworks.algafood.domain.model.Restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // quando a a propriedade é diferente e o modelMapper não encontra um match, podemos mapear
        // qual propriedade da origem será enviada para a destino.
        modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
            .addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
        return modelMapper;
    }
}
