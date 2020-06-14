package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algafood.api.assembler.UsuarioConverter;
import com.algaworks.algafood.api.model.UsuarioDTO;
import com.algaworks.algafood.api.model.input.usuario.SenhaInputDTO;
import com.algaworks.algafood.api.model.input.usuario.UsuarioInputComSenhaDTO;
import com.algaworks.algafood.api.model.input.usuario.UsuarioInputDTO;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	@GetMapping
	public List<UsuarioDTO> listar() {
		return usuarioConverter.toCollectionDTO(cadastroUsuarioService.listar());
	}

	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscar(@PathVariable Long usuarioId) {
		return usuarioConverter.toDto(cadastroUsuarioService.buscar(usuarioId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDTO salvar(@RequestBody @Valid UsuarioInputComSenhaDTO usuarioInputComSenhaDTO) {
		Usuario usuario = usuarioConverter.toDomain(usuarioInputComSenhaDTO);
		usuario = cadastroUsuarioService.adicionar(usuario);

		return usuarioConverter.toDto(usuario);
	}

	@PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInputDTO usuarioInputDTO) {
	    Usuario usuarioAtual = cadastroUsuarioService.buscar(usuarioId);

		usuarioConverter.fromDTOtoDomain(usuarioInputDTO, usuarioAtual);

		usuarioAtual = cadastroUsuarioService.adicionar(usuarioAtual);

		return usuarioConverter.toDto(usuarioAtual);
	}

	@PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInputDTO senhaInputDTO) {
		cadastroUsuarioService.atualizarSenha(usuarioId, senhaInputDTO.getSenhaAtual(),senhaInputDTO.getNovaSenha());
	}

}
