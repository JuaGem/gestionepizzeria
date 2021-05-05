package it.prova.gestionepizzeria.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepizzeria.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>{
	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
}
