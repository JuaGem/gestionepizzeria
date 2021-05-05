package it.prova.gestionepizzeria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import it.prova.gestionepizzeria.web.servlet.auth.LogInServlet;
import it.prova.gestionepizzeria.web.servlet.registration.ExecuteInscrizioneUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.registration.PrepareInscrizioneRegistrationServlet;
import it.prova.gestionepizzeria.web.servlet.utente.ExecuteCambiaStatoUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.ExecuteEditUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.ExecuteInsertUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.ExecuteListUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.ExecuteSearchUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.ExecuteVisualizzaUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.PrepareCambiaStatoUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.PrepareEditUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.PrepareInsertUtenteServlet;
import it.prova.gestionepizzeria.web.servlet.utente.PrepareSearchUtenteServlet;

@Configuration
public class SerlvetRegistrationConfig {

	@Autowired
	private LogInServlet loginServlet;
	@Autowired
	private PrepareInscrizioneRegistrationServlet prepareInscrizioneRegistrationServlet;
	@Autowired
	private ExecuteInscrizioneUtenteServlet executeInscrizioneUtenteServlet;
	@Autowired
	private PrepareSearchUtenteServlet prepareSearchUtenteServlet;
	@Autowired
	private ExecuteSearchUtenteServlet executeSearchUtenteServlet;
	@Autowired
	private ExecuteListUtenteServlet executeListUtenteServlet;
	@Autowired
	private PrepareInsertUtenteServlet prepareInsertUtenteServlet;
	@Autowired
	private ExecuteInsertUtenteServlet executeInsertUtenteServlet;
	@Autowired
	private ExecuteVisualizzaUtenteServlet executeVisualizzaUtenteServlet;
	@Autowired
	private PrepareCambiaStatoUtenteServlet prepareCambiaStatoUtenteServlet;
	@Autowired
	private ExecuteCambiaStatoUtenteServlet executeCambiaStatoUtenteServlet;
	@Autowired
	private PrepareEditUtenteServlet prepareEditUtenteServlet;
	@Autowired
	private ExecuteEditUtenteServlet executeEditUtenteServlet;

	@Bean
	public ServletRegistrationBean<LogInServlet> createLoginServletBean() {
		ServletRegistrationBean<LogInServlet> bean = new ServletRegistrationBean<LogInServlet>(loginServlet,
				"/LoginServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<PrepareInscrizioneRegistrationServlet> createPrepareInscrizioneRegistrationServletBean() {
		ServletRegistrationBean<PrepareInscrizioneRegistrationServlet> bean = new ServletRegistrationBean<PrepareInscrizioneRegistrationServlet>(
				prepareInscrizioneRegistrationServlet, "/registration/PrepareInscrizioneRegistrationServlet");
		return bean;
	}

	@Bean
	public ServletRegistrationBean<ExecuteInscrizioneUtenteServlet> createExecuteInscrizioneUtenteServletBean() {
		ServletRegistrationBean<ExecuteInscrizioneUtenteServlet> bean = new ServletRegistrationBean<ExecuteInscrizioneUtenteServlet>(
				executeInscrizioneUtenteServlet, "/registration/ExecuteInscrizioneUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<PrepareSearchUtenteServlet> createPrepareSearchUtenteServletBean() {
		ServletRegistrationBean<PrepareSearchUtenteServlet> bean = new ServletRegistrationBean<PrepareSearchUtenteServlet>(
				prepareSearchUtenteServlet, "/utente/PrepareSearchUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<ExecuteSearchUtenteServlet> createExecuteSearchUtenteServletBean() {
		ServletRegistrationBean<ExecuteSearchUtenteServlet> bean = new ServletRegistrationBean<ExecuteSearchUtenteServlet>(
				executeSearchUtenteServlet, "/utente/ExecuteSearchUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<ExecuteListUtenteServlet> createExecuteListUtenteServletBean() {
		ServletRegistrationBean<ExecuteListUtenteServlet> bean = new ServletRegistrationBean<ExecuteListUtenteServlet>(
				executeListUtenteServlet, "/utente/ExecuteListUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<PrepareInsertUtenteServlet> createPrepareInsertUtenteServletBean() {
		ServletRegistrationBean<PrepareInsertUtenteServlet> bean = new ServletRegistrationBean<PrepareInsertUtenteServlet>(
				prepareInsertUtenteServlet, "/utente/PrepareInsertUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<ExecuteInsertUtenteServlet> createExecuteInsertUtenteServletBean() {
		ServletRegistrationBean<ExecuteInsertUtenteServlet> bean = new ServletRegistrationBean<ExecuteInsertUtenteServlet>(
				executeInsertUtenteServlet, "/utente/ExecuteInsertUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<ExecuteVisualizzaUtenteServlet> createExecuteVisualizzaUtenteServletBean() {
		ServletRegistrationBean<ExecuteVisualizzaUtenteServlet> bean = new ServletRegistrationBean<ExecuteVisualizzaUtenteServlet>(
				executeVisualizzaUtenteServlet, "/utente/ExecuteVisualizzaUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<PrepareCambiaStatoUtenteServlet> createPrepareCambiaStatoUtenteServletBean() {
		ServletRegistrationBean<PrepareCambiaStatoUtenteServlet> bean = new ServletRegistrationBean<PrepareCambiaStatoUtenteServlet>(
				prepareCambiaStatoUtenteServlet, "/utente/PrepareCambiaStatoUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<ExecuteCambiaStatoUtenteServlet> createExecuteCambiaStatoUtenteServletBean() {
		ServletRegistrationBean<ExecuteCambiaStatoUtenteServlet> bean = new ServletRegistrationBean<ExecuteCambiaStatoUtenteServlet>(
				executeCambiaStatoUtenteServlet, "/utente/ExecuteCambiaStatoUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<PrepareEditUtenteServlet> createPrepareEditUtenteServletBean() {
		ServletRegistrationBean<PrepareEditUtenteServlet> bean = new ServletRegistrationBean<PrepareEditUtenteServlet>(
				prepareEditUtenteServlet, "/utente/PrepareEditUtenteServlet");
		return bean;
	}
	
	@Bean
	public ServletRegistrationBean<ExecuteEditUtenteServlet> createExecuteEditUtenteServletBean() {
		ServletRegistrationBean<ExecuteEditUtenteServlet> bean = new ServletRegistrationBean<ExecuteEditUtenteServlet>(
				executeEditUtenteServlet, "/utente/ExecuteEditUtenteServlet");
		return bean;
	}

}
