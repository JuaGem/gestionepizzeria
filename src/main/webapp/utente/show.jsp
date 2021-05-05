<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		    <div class="card card-body">
		    <dl class="row">
					  <dt class="col-sm-3 text-right">Nome:</dt>
					  <dd class="col-sm-9">${show_utente_attr.nome}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${show_utente_attr.cognome}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Username:</dt>
					  <dd class="col-sm-9">${show_utente_attr.username}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Password:</dt>
					  <dd class="col-sm-9">${show_utente_attr.password}</dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Data Creazione:</dt>
					   <dd class="col-sm-9"><fmt:formatDate type = "date" pattern = "dd/MM/yyyy" value = "${show_utente_attr.dateCreated}" /></dd>
				   	</dl>
				   	<dl class="row">
					  <dt class="col-sm-3 text-right">Stato:</dt>
					  <dd class="col-sm-9">${show_utente_attr.stato}</dd>
				   	</dl>
				   	
				   	<p>
				  <a class="btn btn-primary btn-sm" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
				    Info Ruoli
				  </a>
				</p>
				<div class="collapse" id="collapseExample">
				  <div class="card card-body">
				  
			 		<c:forEach items="${show_utente_attr.ruoli}" var="ruoloItem">
			  			<dl class="row">
					 		 <dt class="col-sm-3 text-right">Codice:</dt>
					  		 <dd class="col-sm-9">${ruoloItem.codice}</dd>
				        </dl>
				   		<dl class="row">
					  		<dt class="col-sm-3 text-right">Descrizione:</dt>
					  		<dd class="col-sm-9">${ruoloItem.descrizione}</dd>
				   		</dl>
				   		
	     		  <br/>
			   </c:forEach>
				    
				  </div>
				</div>
		     
		     </div>
		    	
		    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="ExecuteListUtenteServlet" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>