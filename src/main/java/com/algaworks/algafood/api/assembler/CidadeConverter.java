package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.model.CidadeDTO;
import com.algaworks.algafood.api.model.input.CidadeInputDTO;
import com.algaworks.algafood.domain.model.Cidade;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeConverter implements Converter<Cidade, CidadeDTO, CidadeInputDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cidade toDomain(CidadeInputDTO inputDto) {
       return modelMapper.map(inputDto, Cidade.class);

    }

    @Override
    public CidadeDTO toDto(Cidade domain) {
        return modelMapper.map(domain, CidadeDTO.class);
    }

    @Override
    public List<CidadeDTO> toCollectionDTO(List<Cidade> list) {
        return list.stream().map(cidade -> toDto(cidade)).collect(Collectors.toList());
    }
}
