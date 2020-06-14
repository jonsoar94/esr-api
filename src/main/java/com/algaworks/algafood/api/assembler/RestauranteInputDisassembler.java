package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.restaurante.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Restaurante toDomain(RestauranteInputDTO restauranteInputDTO) {
       return modelMapper.map(restauranteInputDTO, Restaurante.class);
    }

    public void copyToDomain(RestauranteInputDTO restauranteInputDTO, Restaurante restaurante) {
    	restaurante.setCozinha(new Cozinha());
    	
    	if (restaurante.getEndereco() != null) {
    		restaurante.getEndereco().setCidade(new Cidade());
    	}
    	
        modelMapper.map(restauranteInputDTO, restaurante);
    }
}
