package it.prova.gestionepizzeria.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionepizzeria.model.Ruolo;
import it.prova.gestionepizzeria.model.StatoUtente;
import it.prova.gestionepizzeria.model.Utente;
import it.prova.gestionepizzeria.repository.utente.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository repository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<Utente> listAllUtenti() {
		return (List<Utente>) repository.findAll();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		repository.save(utenteInstance);
	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		repository.delete(utenteInstance);
	}
	
	@Transactional
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) {
		utenteEsistente = entityManager.merge(utenteEsistente);
		ruoloInstance = entityManager.merge(ruoloInstance);

		utenteEsistente.getRuoli().add(ruoloInstance);
	}

	@Transactional(readOnly = true)
	public List<Utente> findByExample(Utente example) {
		return repository.findByExample(example);
	}

	@Transactional(readOnly = true)
	public Utente eseguiAccesso(String username, String password) {
		return repository.findByUsernameAndPasswordAndStato(username, password,StatoUtente.ATTIVO);
	}

	@Transactional(readOnly = true)
	public Utente findByUsernameAndPassword(String username, String password) {
		return repository.findByUsernameAndPassword(username, password);
	}
	
	@Transactional(readOnly = true)
	public Utente findOneEagerRuoli(long id) {
		return repository.findOneEagerRuoli(id);
	}
	
	@Transactional(readOnly = true)
	public boolean unicoAdminAttivo() {
		return repository.countByUtenteAdminAttivo() == 1;
	}

}
