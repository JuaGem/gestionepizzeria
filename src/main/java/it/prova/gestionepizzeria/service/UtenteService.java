package it.prova.gestionepizzeria.service;

import java.util.List;

import it.prova.gestionepizzeria.model.Ruolo;
import it.prova.gestionepizzeria.model.Utente;

public interface UtenteService {
	
	public List<Utente> listAllUtenti() ;

	public Utente caricaSingoloUtente(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);

	public List<Utente> findByExample(Utente example);
	
	public Utente findByUsernameAndPassword(String username, String password);
	
	public Utente eseguiAccesso(String username, String password);
	
	public Utente findOneEagerRuoli(long id);
	
	public boolean unicoAdminAttivo();
	
	public void aggiungiRuolo(Utente utenteInstance, Ruolo ruoloInstance);

}
