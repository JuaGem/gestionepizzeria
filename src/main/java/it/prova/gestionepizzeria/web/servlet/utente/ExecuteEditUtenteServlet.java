package it.prova.gestionepizzeria.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.gestionepizzeria.dto.UtenteDTO;
import it.prova.gestionepizzeria.service.RuoloService;
import it.prova.gestionepizzeria.service.UtenteService;

@Component
public class ExecuteEditUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UtenteService utenteService;

	@Autowired
	private RuoloService ruoloService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("idEditInput");
		String usernameParam = request.getParameter("username");
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String passwordParam = request.getParameter("password");
		String passwordRepeatParam = request.getParameter("passwordRepeat");
		String dataCreatedParam = request.getParameter("dateUtente");
		String[] ruoliParam = request.getParameterValues("ruolo.id");

		UtenteDTO utenteforUpdate = UtenteDTO.createUtenteDTOFromParamsWithPass(usernameParam, passwordParam, nomeParam,
				cognomeParam, dataCreatedParam, ruoliParam);
		utenteforUpdate.setId(Long.parseLong(idParam));

		utenteforUpdate.validate(passwordRepeatParam);

		try {
			if (utenteforUpdate.hasErrors()) {

				request.setAttribute("edit_utente_attribute", utenteforUpdate);
				request.setAttribute("ruoli_list_attribute", ruoloService.listAll());
				request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
				return;
			}

			utenteforUpdate.setDateCreated(utenteService.caricaSingoloUtente(Long.parseLong(idParam)).getDateCreated());
			utenteforUpdate.setStato(utenteService.caricaSingoloUtente(Long.parseLong(idParam)).getStato());
			utenteService.aggiorna(utenteforUpdate.BuildUtenteModel());

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");

	}

}
