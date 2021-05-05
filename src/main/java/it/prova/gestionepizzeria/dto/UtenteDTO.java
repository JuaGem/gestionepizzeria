package it.prova.gestionepizzeria.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionepizzeria.model.Ruolo;
import it.prova.gestionepizzeria.model.StatoUtente;
import it.prova.gestionepizzeria.model.Utente;
import it.prova.gestionepizzeria.utility.Utility;

public class UtenteDTO {

	private Long id;
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private Date dateCreated;
	private StatoUtente stato = StatoUtente.CREATO;
	private Set<Ruolo> ruoli = new HashSet<>();

	private List<String> errors = new ArrayList<String>();

	public UtenteDTO() {
	}

	public UtenteDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UtenteDTO(String username, String password, String nome, String cognome) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}

	public UtenteDTO(String username, String password, String nome, String cognome, Date dateCreated) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
	}

	public UtenteDTO(Long id, String username, String password, String nome, String cognome, Date dateCreated,
			StatoUtente stato) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.stato = stato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public void validate() {
		List<String> validationResult = new ArrayList<String>();

		if (StringUtils.isBlank(this.nome))
			validationResult.add("Il campo 'Nome' non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			validationResult.add("Il campo 'Cognome' non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			validationResult.add("Il campo 'username' non può essere vuoto");
		if (StringUtils.isBlank(this.password))
			validationResult.add("Il campo 'password' non può essere vuoto");

		this.setErrors(validationResult);
	}
	
	public void validate(String repeatPass) {
		
		List<String> validationResult = new ArrayList<String>();

		if (StringUtils.isBlank(this.nome))
			validationResult.add("Il campo 'Nome' non può essere vuoto");
		if (StringUtils.isBlank(this.cognome))
			validationResult.add("Il campo 'Cognome' non può essere vuoto");
		if (StringUtils.isBlank(this.username))
			validationResult.add("Il campo 'username' non può essere vuoto");
		if (StringUtils.isBlank(this.password))
			validationResult.add("Il campo 'password' non può essere vuoto");
		if(StringUtils.isBlank(repeatPass))
			validationResult.add("Il campo 'Conferma Password' non puo' essere vuoto");
		if(!this.password.equals(repeatPass))
			validationResult.add("Le due password che hai inserito non coincidono");

		this.setErrors(validationResult);
		
	}

	public boolean hasErrors() {
		return this.errors != null && !this.errors.isEmpty();
	}

	public Utente BuildUtenteModel() {
		Utente result = new Utente(this.id, this.username, this.password, this.nome, this.cognome, this.dateCreated, this.stato);
		if(this.ruoli != null)
			result.setRuoli(this.ruoli);
		
		return result;
	}

	public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel, boolean includeRuoli) {
		UtenteDTO result = new UtenteDTO(utenteModel.getId(), utenteModel.getUsername(), utenteModel.getPassword(),
				utenteModel.getNome(), utenteModel.getCognome(), utenteModel.getDateCreated(), utenteModel.getStato());
		
		if(includeRuoli)
			result.setRuoli(utenteModel.getRuoli());

		return result;
	}

	public static UtenteDTO createUtenteDTOFromParams(String nick, String passw, String nomeParam, String cognomeParam,
			String DataCreatedParam, String statoParam) {

		UtenteDTO result = new UtenteDTO(nick, passw, nomeParam, cognomeParam);

		result.setDateCreated(Utility.parseDateFromString(DataCreatedParam));
		result.setStato(StringUtils.isBlank(statoParam) ? null : StatoUtente.valueOf(statoParam));

		return result;
	}
	
	public static UtenteDTO createUtenteDTOFromParamsWithPass(String nick, String passw, String nomeParam, String cognomeParam,
			String DataCreatedParam, String[] idRuoloArrayInput) {

		UtenteDTO result = new UtenteDTO(nick, passw, nomeParam, cognomeParam);

		result.setDateCreated(Utility.parseDateWithTimeFromString(DataCreatedParam));
		
		Set<Ruolo> insiemeDiRuoli = new HashSet<>();
		if (idRuoloArrayInput != null) {

			for (String idRuoloItem : idRuoloArrayInput) {

				Ruolo ruoloBean = new Ruolo();
				ruoloBean.setId(Long.parseLong(idRuoloItem));
				insiemeDiRuoli.add(ruoloBean);

			}

		}

		result.setRuoli(insiemeDiRuoli);

		return result;
	}
	
	public static Utente createUtenteFromParams(String nick, String nomeParam, String cognomeParam,
			String DataCreatedParam, String statoParam, String[] idRuoloArrayInput) {

		Utente result = new Utente(nick, null, nomeParam, cognomeParam);

		result.setDateCreated(Utility.parseDateFromString(DataCreatedParam));
		result.setStato(StringUtils.isBlank(statoParam) ? null : StatoUtente.valueOf(statoParam));
		
		Set<Ruolo> insiemeDiRuoli = new HashSet<>();
		if (idRuoloArrayInput != null) {

			for (String idRuoloItem : idRuoloArrayInput) {

				Ruolo ruoloBean = new Ruolo();
				ruoloBean.setId(Long.parseLong(idRuoloItem));
				insiemeDiRuoli.add(ruoloBean);

			}

		}

		result.setRuoli(insiemeDiRuoli);

		return result;
	}
	
	public static UtenteDTO createUtenteDTOFromParams(String nick, String nomeParam, String cognomeParam,
			String DataCreatedParam, String statoParam, String[] idRuoloArrayInput) {

		UtenteDTO result = new UtenteDTO(nick, null, nomeParam, cognomeParam);

		result.setDateCreated(Utility.parseDateFromString(DataCreatedParam));
		result.setStato(StringUtils.isBlank(statoParam) ? null : StatoUtente.valueOf(statoParam));
		
		Set<Ruolo> insiemeDiRuoli = new HashSet<>();
		if (idRuoloArrayInput != null) {

			for (String idRuoloItem : idRuoloArrayInput) {

				Ruolo ruoloBean = new Ruolo();
				ruoloBean.setId(Long.parseLong(idRuoloItem));
				insiemeDiRuoli.add(ruoloBean);

			}

		}

		result.setRuoli(insiemeDiRuoli);

		return result;
	}
	
	public static UtenteDTO createUtenteDTOFromParams(String nick, String passw, String nomeParam, String cognomeParam,
			String[] idRuoloArrayInput) {

		UtenteDTO result = new UtenteDTO(nick, passw, nomeParam, cognomeParam);
		
		Set<Ruolo> insiemeDiRuoli = new HashSet<>();
		if (idRuoloArrayInput != null) {

			for (String idRuoloItem : idRuoloArrayInput) {

				Ruolo ruoloBean = new Ruolo();
				ruoloBean.setId(Long.parseLong(idRuoloItem));
				insiemeDiRuoli.add(ruoloBean);

			}

		}

		result.setRuoli(insiemeDiRuoli);

		return result;
	}

	public static List<UtenteDTO> createUtenteDTOListFromModelList(List<Utente> modelListInput, boolean includeRuoli) {
		return modelListInput.stream().map(utenteEntity -> {
			return UtenteDTO.buildUtenteDTOFromModel(utenteEntity, includeRuoli);
		}).collect(Collectors.toList());
	}

}
