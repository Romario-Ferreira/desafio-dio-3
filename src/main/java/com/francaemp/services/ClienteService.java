package com.francaemp.services;

import org.springframework.stereotype.Service;

import com.francaemp.entities.Cliente;

@Service
public interface ClienteService {

	Iterable<Cliente> buscarTodos();
	
	Cliente buscarPorId(Long id);
	
	void inserir(Cliente obj);
	
	void atualizar (Long id, Cliente obj);
	
	void deletar(Long id);
}
