package it.prova.gestionepizzeria.service;

import java.util.List;

import it.prova.gestionepizzeria.model.Ingrediente;

public interface IngredienteService {
	
	public List<Ingrediente> listAll();

	public Ingrediente caricaSingoloIngrediente(Long id);

	public void aggiorna(Ingrediente ingredienteInstance);

	public void inserisciNuovo(Ingrediente ingredienteInstance);

	public void rimuovi(Ingrediente ingredienteInstance);

}
