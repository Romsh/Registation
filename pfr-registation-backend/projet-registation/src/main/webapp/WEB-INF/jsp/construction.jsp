<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<%@ include file="css.jsp"%>
<title>Appli en construction !</title>
<!-- 	<link href="https://cdnjs.cloudflare.com/ajax/libs/simple-line-icons/2.4.1/css/simple-line-icons.min.css" rel="stylesheet"> -->
<!-- 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="../css/style.css">
</head>

<body>
	
	<%@ include file="topnavbar.jsp"%>
		<%@ include file="sidemenu.jsp"%>
	<div id="wrapper" class="corps">

		<div class="container p-3 my-3 bg-dark text-white">
			<h1>Appli en construction, revenez dans quelques jours !</h1>
			<h2>La team</h2>
		</div>

		<div class="container">
			<div id="div-connection" style="display: none;">
				<div class="connexion-form">
					<form class="connexion-form">
						<div class="form-icon">
							<span><i class="fas fa-user-alt"></i></span>
						</div>
						<div class="form-group">
							<input type="text" class="form-control item" id="loginInput"
								placeholder="Matricule">
						</div>
						<div class="form-group">
							<input type="password" class="form-control item"
								id="passwordInput" placeholder="********">
						</div>
						<div class="form-group">
							<button type="submit" id="loginBtn" class="btn btn-block connect">Se
								Connecter</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
	</div>
	<%@ include file="js.jsp"%>
</body>

	

</html>