package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.model.RestauranteDTO;
import com.algaworks.algafood.api.model.input.RestauranteInputDTO;
import com.algaworks.algafood.domain.model.Restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteConverter implements Converter<Restaurante, RestauranteDTO, RestauranteInputDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Restaurante toDomain(RestauranteInputDTO dto) {
        return modelMapper.map(dto, Restaurante.class);
    }

    @Override
    public RestauranteDTO toDto(Restaurante domain) {
        return modelMapper.map(domain, RestauranteDTO.class);
    }

    @Override
    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> list) {
        return list.stream().map(restaurante -> toDto(restaurante)).collect(Collectors.toList());
    }
}
