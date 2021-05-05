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
public class PrepareEditUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RuoloService ruoloService;

    public PrepareEditUtenteServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParameter = request.getParameter("idUtente");

        try {

        	UtenteDTO result = UtenteDTO.buildUtenteDTOFromModel(utenteService.findOneEagerRuoli(Long.parseLong(idParameter)), true);
        	
            request.setAttribute("edit_utente_attribute", result);
            request.setAttribute("ruoli_list_attribute", ruoloService.listAll());

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Attenzione, si è verificato un errore.");
            request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
            return;
        }
        
        request.getRequestDispatcher("/utente/edit.jsp").forward(request, response);
    }
}
