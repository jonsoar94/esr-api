package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.model.input.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomain(RestauranteInputDTO restauranteInputDTO) {
       return modelMapper.map(restauranteInputDTO, Restaurante.class);
    }

    public void copyToDomain(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante) {
        modelMapper.map(restauranteInputDTO, restaurante);
    }
}
