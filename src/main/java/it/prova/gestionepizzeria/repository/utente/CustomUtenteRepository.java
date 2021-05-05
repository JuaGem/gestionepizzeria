package it.prova.gestionepizzeria.repository.utente;

import java.util.List;

import it.prova.gestionepizzeria.model.Utente;



public interface CustomUtenteRepository {
	
	List<Utente> findByExample(Utente example);
	
	Utente findOneEagerRuoli(Long id);
	
	Long countByUtenteAdminAttivo();

}
