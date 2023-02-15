<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="css.jsp"%>
<title>Calendrier des évènements</title>
</head>
<body>
	<div id="wrapper">

		<%@ include file="topnavbar.jsp"%>
		<%@ include file="sidemenu.jsp"%>

		<div id="page-content-wrapper">
			<div class="page-content">
				<div class="container-fluid"> 
				
					
          			  <div class="evenement-titre">
	          			 	 <div class="row">
	          			 		 <div id="btnAujoudhui" class="col-sm-2"> 
	          			 		 	<button class="btn">Aujourd'hui</button>
	          			 		 </div>
	          					
		          			 	 <div class="col-sm-10" id="affichageMois">
		          			 	 	<h3>	
		          			 	 			<button name="action" id="actionGauche" value="p" class="btn btn-secondary font-weight-bold"><</button>
			          			 	 		<span id="calTitre"></span>
			          			 	 		<button name="action" id="actionDroite" value="s" class="btn btn-secondary font-weight-bold">></button>
			          			 	 		<input id="anneeActuel" name="year" type="hidden">
			          			 	 		<input id="moisActuel" name="month" type="hidden">
			          			 	 </h3>
		          			 	 </div>
	          			 	 </div>
          			  </div>
				
						<div id="calendarHere">
						</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="js.jsp"%>
</body>
</html>