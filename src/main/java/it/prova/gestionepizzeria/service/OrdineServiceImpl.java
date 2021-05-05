package it.prova.gestionepizzeria.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepizzeria.model.Ordine;
import it.prova.gestionepizzeria.repository.ordine.OrdineRepository;

@Service
public class OrdineServiceImpl implements OrdineService {
	
	@Autowired
	private OrdineRepository ordineRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Ordine> listAll() {
		return (List<Ordine>) ordineRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Ordine caricaSingoloOrdine(Long id) {
		return ordineRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
	}

	@Transactional
	public void inserisciNuovo(Ordine ordineInstance) {
		ordineRepository.save(ordineInstance);
	}

	@Transactional
	public void rimuovi(Ordine ordineInstance) {
		
		ordineInstance = entityManager.merge(ordineInstance);
		
		if(!ordineInstance.getPizze().isEmpty())
			throw new RuntimeException("Ci sono delle pizze associate a dei ordini, impossibile rimuovere");
		
		ordineRepository.delete(ordineInstance);
	}

}
