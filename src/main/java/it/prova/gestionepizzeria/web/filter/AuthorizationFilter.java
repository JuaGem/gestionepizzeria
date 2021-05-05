package it.prova.gestionepizzeria.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//affidiamo i filtri a spring e non specificando altro = urlPatterns = { "/*" }
@Component
@Order(2)
public class AuthorizationFilter implements Filter {

	public static final String[] ADMIN_URLS = { "/utente/" };
	public static final String[] PIZZAIOLO_URLS = { "/pizzaiolo/" };
	public static final String[] FATTORINO_URLS = { "/fattorino/" };

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// prendo il path della request che sta passando in questo momento es.
		// /LoginServlet
		String pathAttuale = httpRequest.getServletPath();
		System.out.println("Invocazione di: " + pathAttuale);

		// il controllo dell'utente in sessione stato fatto da AuthenticationFilter
		// vediamo se il path risulta tra quelli 'liberi di passare'
		// se non lo e' bisogna controllare i percorsi protetti
		// controllo che utente abbia ruolo admin se nel path risulta presente /admin/
		if (!AuthenticationFilter.isPathInWhiteList(pathAttuale) && isPathForOnlyAdministrators(pathAttuale)
				&& !AuthenticationFilter.getUserLoggedIn(httpRequest).isAdmin()) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home?operationResult=NOT_ALLOWED");
			return;
		}
		if (!AuthenticationFilter.isPathInWhiteList(pathAttuale) && isPathForOnlyPizzaioli(pathAttuale)
				&& !AuthenticationFilter.getUserLoggedIn(httpRequest).isPizzaiolo()) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home?operationResult=NOT_ALLOWED");
			return;
		}
		if (!AuthenticationFilter.isPathInWhiteList(pathAttuale) && isPathForOnlyFattorini(pathAttuale)
				&& !AuthenticationFilter.getUserLoggedIn(httpRequest).isFattorino()) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home?operationResult=NOT_ALLOWED");
			return;
		}

		chain.doFilter(request, response);
	}

	private static boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : ADMIN_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile liberamente");
				return true;
			}
		}
		return false;
	}

	private static boolean isPathForOnlyPizzaioli(String requestPath) {
		for (String urlPatternItem : PIZZAIOLO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile liberamente");
				return true;
			}
		}
		return false;
	}

	private static boolean isPathForOnlyFattorini(String requestPath) {
		for (String urlPatternItem : FATTORINO_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				System.out.println("url invocabile liberamente");
				return true;
			}
		}
		return false;
	}

}
