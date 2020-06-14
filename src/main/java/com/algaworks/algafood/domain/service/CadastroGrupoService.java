package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_ENTIDADE_EM_USO = "O grupo não pode ser excluido, pois está em uso.";
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Grupo> listar() {
		return grupoRepository.findAll();
	}
	
	public Grupo buscar(Long grupoId) {
		return grupoRepository.findById(grupoId)
				.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
	
	@Transactional
	public Grupo adicionar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public Grupo atualizar(Long grupoId, Grupo grupo) {
		Grupo grupoExistente = buscar(grupoId);
		
		BeanUtils.copyProperties(grupo, grupoExistente, "id");
		
		return grupoRepository.save(grupoExistente);
	}
	
	@Transactional
	public void deletar(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(MSG_ENTIDADE_EM_USO);
		}
	}
}
