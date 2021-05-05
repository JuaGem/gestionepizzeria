package it.prova.gestionepizzeria;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.gestionepizzeria.model.Ruolo;
import it.prova.gestionepizzeria.model.StatoUtente;
import it.prova.gestionepizzeria.model.Utente;
import it.prova.gestionepizzeria.service.RuoloService;
import it.prova.gestionepizzeria.service.UtenteService;



@SpringBootApplication
public class GestionepizzeriaApplication implements CommandLineRunner{
	
	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(GestionepizzeriaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE") == null) 
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ADMIN_ROLE"));

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE") == null) 
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo User", "PIZZAIOLO_ROLE"));
		
		if(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE") == null)
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino User", "FATTORINO_ROLE"));

		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Giovanni", "Gemini", new Date());
			admin.setStato(StatoUtente.ATTIVO);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE"));
			utenteServiceInstance.inserisciNuovo(admin);
//			utenteServiceInstance.aggiungiRuolo(admin,
//					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("user", "user") == null) {
			Utente pizzaiolo = new Utente("user", "user", "Antonio", "Verdi", new Date());
			pizzaiolo.setStato(StatoUtente.ATTIVO);
			pizzaiolo.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE"));
			utenteServiceInstance.inserisciNuovo(pizzaiolo);
//			utenteServiceInstance.aggiungiRuolo(pizzaiolo,
//					ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "PIZZAIOLO_ROLE"));
		}
		
		if(utenteServiceInstance.findByUsernameAndPassword("fatt", "fatt") == null) {
			Utente fattorino = new Utente("fatt","fatt","Andrea","Vecchiato", new Date());
			fattorino.setStato(StatoUtente.ATTIVO);
			fattorino.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE"));
			utenteServiceInstance.inserisciNuovo(fattorino);
//			utenteServiceInstance.aggiungiRuolo(fattorino, 
//					ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE"));
		}
		
	}

}
