var utilisateurEnCours;

$(document).ready(function() {

	utilisateurEnCours = JSON.parse(sessionStorage.getItem("utilisateur_en_cours"));
/*Menu-toggle*/
$("#menu-toggle").click(function(e) {
	e.preventDefault();
	$("#wrapper").toggleClass("active");
});

$("#co_ins").mouseover(function() {
	$("#co_ins").toggleClass("active");
});

$("#co_ins").mouseout(function() {
	$("#co_ins").toggleClass("active");
});

$('#loginBtn').click(function() {
	event.preventDefault();
	$.ajax({
		url : 'login',
		type : 'POST',
		data : {
			username : $('#loginInput').val(),
			password : $('#passwordInput').val()
		},
		success : function() {
			$('#loginInput').val('');
			$('#passwordInput').val('');
			alert("ok authentification");
			infoUserEnCours();
			top.location.href ='responsable/accueil';
		},
		error : function(responseHttp) {
			if (responseHttp.status == 401) {
				$('#loginMsgText').text('mauvais login/mot de passe');
				alert("Erreur authentification");
			} else {
				console.log(responseHttp);
			}
		},
	});
});
$('#loginMsgBtn').click(function() {
	$('#loginMsgDiv').css('display', 'none');
});

$('#co_ins').click(function() {
	$('#div-connection').css('display', 'block');
});



function infoUserEnCours() {
	$.ajax({
		url : 'user',
		type : 'GET',
		success : function(reponse) {
			sessionStorage.setItem("utilisateur_en_cours", JSON.stringify(reponse));
			utilisateurEnCours = reponse;
			affichageBouttonSelonRole(utilisateurEnCours);
		},
		error : function(responseHttp) {
			if (responseHttp.status == 401) {
				$('#loginMsgDiv').addClass('alert-danger');
				$('#loginMsgDiv').removeClass('alert-primary');
				$('#loginMsgText').text('mauvais login/mot de passe');
				$('#loginMsgDiv').css('display', 'block');
			} else {
				console.log(responseHttp);
			}
		},
	});
}

//affichageBouttonSelonRole(utilisateurEnCours);
function affichageBouttonSelonRole(utilisateurEnCours) {

	if (!utilisateurEnCours) {
		$('#loginLien').css('display', 'block').parent().css('margin-left', 'auto');
		$('#logoutLien').css('display', 'none').parent().css('margin-left', '');
		$('#lienDashboard').css('display', 'none');
		$('.actionTd').css('display', 'none');
	} else {
		$('#logoutLien').css('display', 'block').parent().css('margin-left', 'auto');
		$('#loginLien').css('display', 'none').parent().css('margin-left', '');
		rendreLignCliquablePourDetail();

		if ('ROLE_RESPONSABLE' == utilisateurEnCours.role) {
			$('.actionTd').css('display', 'block');
			$('#lienDashboard').css('display', 'block');

		} else if ('ROLE_EMPLOYE' == utilisateurEnCours.role) {
			$('#ajoutLien').css('display', 'none');
			$('#lienDashboard').css('display', 'none');

		} else {
			logout();
		}
	}
}

function logout() {
	utilisateurEnCours = null;
	sessionStorage.removeItem("utilisateur_en_cours");
	$('#listLien').click();
	$('.etudiantTr td:not(:last-child)').off();
	$('#ajoutLien').css('display', 'none');
	$('.actionTd').css('display', 'none');
	$('#etudiantDetailDiv').css('display', 'none');
	$('#loginLien').css('display', 'block').parent().css('margin-left', 'auto');
	$('#logoutLien').css('display', 'none').parent().css('margin-left', '');
	$('#ajoutMsgDiv').css('display', 'none');
	$('#loginMsgDiv').css('display', 'none');
	$('#deleteMsgDiv').css('display', 'none');
}
})