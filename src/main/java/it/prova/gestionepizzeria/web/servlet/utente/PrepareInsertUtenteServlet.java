package it.prova.gestionepizzeria.web.servlet.utente;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.gestionepizzeria.dto.UtenteDTO;
import it.prova.gestionepizzeria.model.Ruolo;
import it.prova.gestionepizzeria.service.RuoloService;

@Component
public class PrepareInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	private RuoloService ruoloService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Ruolo> ruoli = ruoloService.listAll();
		request.setAttribute("ruoli_list_attribute", ruoli);
		
		request.setAttribute("insert_utente_attr", new UtenteDTO());
		request.getRequestDispatcher("/utente/insert.jsp").forward(request, response);
	}

}
