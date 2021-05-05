package it.prova.gestionepizzeria.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codice")
	private String codice;
	@Column(name = "costo_totale")
	private Double costoTotale;
	@Column(name = "data_ordine")
	private Date dataOrdine;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "utente_id")
	private Utente utente;
	
	@ManyToMany
	@JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
	private List<Pizza> pizze = new ArrayList<>();
	
	public Ordine() {
	}

	public Ordine(String codice, Double costoTotale, Date dataOrdine) {
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.dataOrdine = dataOrdine;
	}

	public Ordine(String codice, Double costoTotale, Date dataOrdine, Cliente cliente, Utente utente) {
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.dataOrdine = dataOrdine;
		this.cliente = cliente;
		this.utente = utente;
	}
	
	public Ordine(String codice, Double costoTotale) {
		this.codice = codice;
		this.costoTotale = costoTotale;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(Double costoTotale) {
		this.costoTotale = costoTotale;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

}
