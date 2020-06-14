package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public Usuario buscar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	public Usuario adicionar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public void atualizarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuarioAtual = buscar(usuarioId);

		if(!usuarioAtual.senhasIdenticas(senhaAtual)) {
			throw new NegocioException("Senha atual não coincide com a senha do usuário já existente.");
		}

		usuarioAtual.setSenha(novaSenha);

		adicionar(usuarioAtual);
	}
}
