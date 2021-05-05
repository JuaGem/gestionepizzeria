package it.prova.gestionepizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepizzeria.model.Cliente;
import it.prova.gestionepizzeria.repository.cliente.ClienteRepository;

public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional(readOnly = true)
	public List<Cliente> listAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Cliente caricaSingoloCliente(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);
	}

	@Transactional
	public void inserisciNuovo(Cliente clienteInstance) {
		clienteRepository.save(clienteInstance);
	}

	@Transactional
	public void rimuovi(Cliente clienteInstance) {
		clienteRepository.delete(clienteInstance);
	}

}
