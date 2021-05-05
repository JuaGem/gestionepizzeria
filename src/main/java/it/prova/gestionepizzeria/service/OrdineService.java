package it.prova.gestionepizzeria.service;

import java.util.List;

import it.prova.gestionepizzeria.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll();

	public Ordine caricaSingoloOrdine(Long id);

	public void aggiorna(Ordine ordineInstance);

	public void inserisciNuovo(Ordine ordineInstance);

	public void rimuovi(Ordine ordineInstance);

}
