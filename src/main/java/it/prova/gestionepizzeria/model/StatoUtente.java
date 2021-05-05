package it.prova.gestionepizzeria.model;

public enum StatoUtente {
	ATTIVO,DISABILITATO,CREATO;
	
	public static StatoUtente getStatoUtenteByString(String valore) {
		StatoUtente[] stati = StatoUtente.values();
		for (StatoUtente statoItem : stati) 
			if (valore.equalsIgnoreCase(statoItem.name())) 
				return statoItem;
			
	 return null;
	}

}
