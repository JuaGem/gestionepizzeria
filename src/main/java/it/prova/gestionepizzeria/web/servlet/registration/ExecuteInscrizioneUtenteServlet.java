package it.prova.gestionepizzeria.web.servlet.registration;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.gestionepizzeria.dto.UtenteDTO;
import it.prova.gestionepizzeria.model.StatoUtente;
import it.prova.gestionepizzeria.service.UtenteService;

@Component
public class ExecuteInscrizioneUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UtenteService serviceUtente;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String passwordRepeatParam = request.getParameter("confermaPassword");
		
		UtenteDTO utenteInstance = UtenteDTO.createUtenteDTOFromParams(usernameParam, passwordParam, nomeParam,
				cognomeParam, null);
		
		utenteInstance.validate(passwordRepeatParam);
		
		if (utenteInstance.hasErrors()) {
			request.setAttribute("registration_utente_attr", utenteInstance);
			request.getRequestDispatcher("/registration/insert.jsp").forward(request, response);
			return;
		}
		
		utenteInstance.setDateCreated(new Date());
		utenteInstance.setStato(StatoUtente.CREATO);
		
		try {
			
			if(serviceUtente.findByUsernameAndPassword(usernameParam, passwordParam).getUsername().equals(utenteInstance.getUsername())) {
				request.setAttribute("errorMessage", "Nickname già in uso, scegline un altro!!!");
				request.getRequestDispatcher("/registration/insert.jsp").forward(request, response);
				return;
			}
			
			
			serviceUtente.inserisciNuovo(utenteInstance.BuildUtenteModel());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/registration/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/home");
	}

}
