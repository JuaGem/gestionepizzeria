package it.prova.gestionepizzeria.service;

import java.util.List;

import it.prova.gestionepizzeria.model.Pizza;

public interface PizzaService {
	
	public List<Pizza> listAll();

	public Pizza caricaSingoloPizza(Long id);

	public void aggiorna(Pizza pizzaInstance);

	public void inserisciNuovo(Pizza pizzaInstance);

	public void rimuovi(Pizza pizzaInstance);

}
