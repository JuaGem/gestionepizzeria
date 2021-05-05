package it.prova.gestionepizzeria.service;

import java.util.List;

import it.prova.gestionepizzeria.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> listAll();

	public Cliente caricaSingoloCliente(Long id);

	public void aggiorna(Cliente clienteInstance);

	public void inserisciNuovo(Cliente clienteInstance);

	public void rimuovi(Cliente clienteInstance);

}
