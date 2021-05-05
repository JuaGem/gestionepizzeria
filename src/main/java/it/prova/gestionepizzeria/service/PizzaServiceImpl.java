package it.prova.gestionepizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepizzeria.model.Pizza;
import it.prova.gestionepizzeria.repository.pizza.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public List<Pizza> listAll() {
		return (List<Pizza>) pizzaRepository.findAll();
	}

	@Override
	public Pizza caricaSingoloPizza(Long id) {
		return pizzaRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
	}

	@Override
	public void inserisciNuovo(Pizza pizzaInstance) {
		pizzaRepository.save(pizzaInstance);
	}

	@Override
	public void rimuovi(Pizza pizzaInstance) {
		pizzaRepository.delete(pizzaInstance);
	}

}
