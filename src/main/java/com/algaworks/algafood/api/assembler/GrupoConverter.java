package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.GrupoDTO;
import com.algaworks.algafood.api.model.input.grupo.GrupoInputDTO;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoConverter implements Converter<Grupo, GrupoDTO, GrupoInputDTO> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Grupo toDomain(GrupoInputDTO grupoInputDTO) {
		return modelMapper.map(grupoInputDTO, Grupo.class);
	}

	@Override
	public GrupoDTO toDto(Grupo grupo) {
		return modelMapper.map(grupo, GrupoDTO.class);
	}

	@Override
	public List<GrupoDTO> toCollectionDTO(List<Grupo> grupos) {
		return grupos.stream().map(grupo -> toDto(grupo)).collect(Collectors.toList());
	}

}
