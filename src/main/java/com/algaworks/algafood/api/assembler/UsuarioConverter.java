package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.UsuarioDTO;
import com.algaworks.algafood.api.model.input.usuario.UsuarioInputComSenhaDTO;
import com.algaworks.algafood.api.model.input.usuario.UsuarioInputDTO;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioConverter implements Converter<Usuario, UsuarioDTO, UsuarioInputDTO> {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Usuario toDomain(UsuarioInputDTO usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}

	@Override
	public UsuarioDTO toDto(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public List<UsuarioDTO> toCollectionDTO(List<Usuario> usuarios) {
		return usuarios.stream().map(this::toDto).collect(Collectors.toList());
	}

	public void fromDTOtoDomain(UsuarioInputDTO usuarioInputDTO, Usuario usuario) {
	    modelMapper.map(usuarioInputDTO, usuario);
    }
}
