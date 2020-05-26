package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.model.EstadoDTO;
import com.algaworks.algafood.api.model.input.EstadoInputDTO;
import com.algaworks.algafood.domain.model.Estado;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoConverter implements Converter<Estado, EstadoDTO, EstadoInputDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Estado toDomain(EstadoInputDTO inputDto) {
       return modelMapper.map(inputDto, Estado.class);
    }

    @Override
    public EstadoDTO toDto(Estado domain) {
        return modelMapper.map(domain, EstadoDTO.class);
    }

    @Override
    public List<EstadoDTO> toCollectionDTO(List<Estado> list) {
        return list.stream().map(estado -> toDto(estado)).collect(Collectors.toList());
    }

}
