package it.prova.gestionepizzeria.web.servlet.utente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.gestionepizzeria.dto.UtenteDTO;
import it.prova.gestionepizzeria.model.StatoUtente;
import it.prova.gestionepizzeria.service.UtenteService;

@Component
public class ExecuteCambiaStatoUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private UtenteService serviceUtente;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUtenteParam = request.getParameter("idUtente");

		if (!NumberUtils.isCreatable(idUtenteParam)) {
			
			request.setAttribute("errorMessage", "Attenzione si è verificato un erroreeeee.");
			request.getRequestDispatcher("utente/list.jsp").forward(request, response);
			return;
		}

		try {
			UtenteDTO utenteDaModificare = UtenteDTO.buildUtenteDTOFromModel(serviceUtente.findOneEagerRuoli(Long.parseLong(idUtenteParam)), true);

			if (utenteDaModificare.BuildUtenteModel().isAdmin() && serviceUtente.unicoAdminAttivo()
					&& utenteDaModificare.getStato().name().equalsIgnoreCase(StatoUtente.ATTIVO.name())) {
				
				request.setAttribute("errorMessage", "Non posso disattivare l'unico admin attivo.");
				request.setAttribute("utenti_list_attribute", serviceUtente.listAllUtenti());
				request.getRequestDispatcher("/utente/list.jsp").forward(request, response);
				
			 return;
			}
			
			if (utenteDaModificare.getStato() == StatoUtente.ATTIVO) {
				utenteDaModificare.setStato(StatoUtente.DISABILITATO);
			} else {
				utenteDaModificare.setStato(StatoUtente.ATTIVO);
			}

			serviceUtente.aggiorna(utenteDaModificare.BuildUtenteModel());

			request.setAttribute("utenti_list_attribute", serviceUtente.listAllUtenti());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");
	}


}
