package it.prova.gestionepizzeria.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.gestionepizzeria.dto.UtenteDTO;
import it.prova.gestionepizzeria.model.Utente;
import it.prova.gestionepizzeria.service.UtenteService;


@Component
public class ExecuteSearchUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Autowired
    private UtenteService utenteService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String userParam = request.getParameter("username");
		String dataParam = request.getParameter("dateCreated");
		String statoParam = request.getParameter("stato");
		String[] ruoliParam = request.getParameterValues("ruolo.id");
		
		
		
		
		try {
			
			Utente example = UtenteDTO.createUtenteFromParams(userParam, nomeParam, cognomeParam, dataParam, statoParam, ruoliParam);
			
//			UtenteDTO example = UtenteDTO.createUtenteDTOFromParams(nomeParam, cognomeParam, userParam, dataParam, statoParam, ruoliParam);
			
			request.setAttribute("utenti_list_attribute", utenteService.findByExample(example));
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/utente/list.jsp").forward(request, response);
	}

}
