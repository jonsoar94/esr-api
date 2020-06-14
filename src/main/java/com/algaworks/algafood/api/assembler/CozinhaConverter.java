package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.model.CozinhaDTO;
import com.algaworks.algafood.api.model.input.cozinha.CozinhaInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaConverter implements Converter<Cozinha, CozinhaDTO, CozinhaInputDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cozinha toDomain(CozinhaInputDTO inputDto) {
       return modelMapper.map(inputDto, Cozinha.class);
    }

    @Override
    public CozinhaDTO toDto(Cozinha domain) {
        return modelMapper.map(domain, CozinhaDTO.class);
    }

    @Override
    public List<CozinhaDTO> toCollectionDTO(List<Cozinha> list) {
        return list.stream().map(cozinha -> toDto(cozinha)).collect(Collectors.toList());
    }
}
