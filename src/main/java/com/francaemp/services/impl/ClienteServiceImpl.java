package com.francaemp.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.francaemp.entities.Cliente;
import com.francaemp.entities.Endereco;
import com.francaemp.repositories.ClienteRepository;
import com.francaemp.repositories.EnderecoRepository;
import com.francaemp.services.ClienteService;
import com.francaemp.services.ViaCepSerivce;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepSerivce viaCepService;
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente obj) {
		salvarClienteComCep(obj);
	}

	@Override
	public void atualizar(Long id, Cliente obj) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if(clienteBd.isPresent()) {
			salvarClienteComCep(obj);
		}
		
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
	
	private void salvarClienteComCep(Cliente obj) {
		String cep = obj.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		obj.setEndereco(endereco);
		clienteRepository.save(obj);
	}


}
