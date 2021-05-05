package it.prova.gestionepizzeria.repository.utente;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepizzeria.model.StatoUtente;
import it.prova.gestionepizzeria.model.Utente;


public interface UtenteRepository extends CrudRepository<Utente, Long>, CustomUtenteRepository{
	
	Utente findByUsernameAndPassword(String username, String password);
	
	@EntityGraph(attributePaths = { "ruoli" })
	Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
	
}
