<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>


<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Elimina elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	
		
		<div class="card card-body">
		    <dl class="row">
					  <dt class="col-sm-3 text-right">Nome:</dt>
					  <dd class="col-sm-9">${elimina_utente_attr.nome}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${elimina_utente_attr.cognome}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Username:</dt>
					  <dd class="col-sm-9">${elimina_utente_attr.username}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Password:</dt>
					  <dd class="col-sm-9">${elimina_utente_attr.password}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Creazione:</dt>
					   <dd class="col-sm-9"><fmt:formatDate type = "date" pattern = "dd/MM/yyyy" value = "${elimina_utente_attr.dateCreated}" /></dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Stato:</dt>
					  <dd class="col-sm-9">${elimina_utente_attr.stato}</dd>
				   	</dl>
		     
		     </div>
		    
		    <div class='card-footer'>
		    
		    	<form action="ExecuteCambiaStatoUtenteServlet" method="post"   >
			        <a href="ExecuteListUtenteServlet" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
		        
		        	
		        	<input type="hidden"   name="idUtente"   value="${elimina_utente_attr.id }">
		        	
		        	<c:if test = "${elimina_utente_attr.stato == 'ATTIVO'}">
                         <button type="submit" class='btn btn-outline-secondary' style='color: black;background-color: red'>Disabilita</button>
                    </c:if>
                    <c:if test = "${elimina_utente_attr.stato == 'DISABILITATO' || elimina_utente_attr.stato == 'CREATO'}">
                          <button type="submit" class='btn btn-outline-secondary' style='color: black;background-color: green'>Abilita</button>
                    </c:if>
                    
                    
		        
		        </form>
		        
		        
		    </div>
		    
		    
		 
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>