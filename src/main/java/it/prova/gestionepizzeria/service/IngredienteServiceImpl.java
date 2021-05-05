package it.prova.gestionepizzeria.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepizzeria.model.Ingrediente;
import it.prova.gestionepizzeria.repository.ingrediente.IngredienteRepository;

@Service
public class IngredienteServiceImpl implements IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Ingrediente> listAll() {
		return (List<Ingrediente>) ingredienteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Ingrediente caricaSingoloIngrediente(Long id) {
		return ingredienteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Ingrediente ingredienteInstance) {
		ingredienteRepository.save(ingredienteInstance);
	}

	@Transactional
	public void inserisciNuovo(Ingrediente ingredienteInstance) {
		ingredienteRepository.save(ingredienteInstance);
	}

	@Transactional
	public void rimuovi(Ingrediente ingredienteInstance) {
		ingredienteInstance = entityManager.merge(ingredienteInstance);
		
		if(!ingredienteInstance.getPizze().isEmpty())
			throw new RuntimeException("Ci sono delle pizze associate a dei ingredienti, impossibile rimuovere");
		
		ingredienteRepository.delete(ingredienteInstance);
	}

}
