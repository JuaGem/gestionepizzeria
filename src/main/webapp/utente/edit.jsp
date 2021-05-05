<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Modifica Elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<%-- alert con lista errori --%>
		<div class="alert alert-danger ${edit_utente_attribute.hasErrors()?'':'d-none'}" role="alert">
			<c:forEach var ="errorItem" items="${edit_utente_attribute.errors }">
	        	<ul>
					<li> ${errorItem }</li>	
				</ul>
	      	</c:forEach>
		</div>
	
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica elemento</h5> 
		    </div>
		    <div class='card-body'>
		    
		    		<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form method="post" action="ExecuteEditUtenteServlet" novalidate="novalidate" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Username<span class="text-danger">*</span></label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire username" value="${edit_utente_attribute.username }" autocomplete="nope" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Nome<span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire nome" value="${edit_utente_attribute.nome }" required>
							</div>
						</div>
						
						<div class="form-row">
						
							<div class="form-group col-md-6">
								<label>Cognome<span class="text-danger">*</span></label>
								<input type="text" class="form-control" name="cognome" id="cognome" placeholder="Inserire cognome" value="${edit_utente_attribute.cognome }" required>
							</div>
						
							<div class="form-group col-md-6">
								<label>Password<span class="text-danger">*</span></label>
								<input type="password" class="form-control" name="password" id="password" placeholder="Inserire password" value="${edit_utente_attribute.password }" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Reinserisci Password<span class="text-danger">*</span></label>
								<input type="password" class="form-control" name="passwordRepeat" id="passwordRepeat" placeholder="Inserire nuovamente password" value="" required>
							</div>
						
						</div>
							
						Ruoli:
                        <div class="form-check">
                          <c:forEach items="${ruoli_list_attribute}" var="ruoloItem">
	                          <input name="ruolo.id" class="form-check-input" type="checkbox" value="${ruoloItem.id}" id="defaultCheck"  ${edit_utente_attribute.ruoli.contains(ruoloItem)?"checked":"" } >
	                          <label class="form-check-label" for="defaultCheck">
	                            ${ruoloItem.descrizione}
	                          </label>
                          <br/>
                          </c:forEach>
                        </div>
							
						<input type="hidden" name="idEditInput" value="${edit_utente_attribute.id}">
						<input type="hidden"   name="dateUtente" value="${edit_utente_attribute.dateCreated}">
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>