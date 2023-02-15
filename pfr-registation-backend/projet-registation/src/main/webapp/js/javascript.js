var utilisateurEnCours;
$(document).ready(
    function () {
        utilisateurEnCours = JSON.parse(sessionStorage
            .getItem("utilisateur_en_cours"));
        /* Menu-toggle */
        $("#menu-toggle").click(function (e) {
            e.preventDefault();
            $("#wrapper").toggleClass("active");
        });
        $("#co_ins").mouseover(function () {
            $("#co_ins").toggleClass("active");
        });
        $("#co_ins").mouseout(function () {
            $("#co_ins").toggleClass("active");
        });
        $('#loginBtn').click(
            function () {
                event.preventDefault();
                $.ajax({
                    url: 'login',
                    type: 'POST',
                    data: {
                        username: $('#loginInput').val(),
                        password: $('#passwordInput').val()
                    },
                    success: function () {
                        $('#loginInput').val('');
                        $('#passwordInput').val('');
                        alert("ok authentification");
                        infoUserEnCours();
                        affichageBouttonSelonRole(utilisateurEnCours);
                        console.log(utilisateurEnCours);
                        top.location.href = 'responsable/accueil';
                    },
                    error: function (responseHttp) {
                        if (responseHttp.status == 401) {
                            $('#loginMsgText').text(
                                'mauvais login/mot de passe');
                            alert("Erreur authentification");
                        } else {
                            console.log(responseHttp);
                        }
                    },
                });
            });
        $('#loginMsgBtn').click(function () {
            $('#loginMsgDiv').css('display', 'none');
        });
        $('#co_ins').click(function () {
            $('#div-connection').css('display', 'block');
        });
        
        $('#crashTest').click(function () {
            alert(utilisateurEnCours);
            console.log(utilisateurEnCours);
            if(utilisateurEnCours.responsable == true){
            	  $('#crashTest').css('display', 'none');
            }
        });
        affichageBouttonSelonRole(utilisateurEnCours);
    }
)

function infoUserEnCours() {
    $.ajax({
        url: 'user',
        type: 'GET',
        success: function (reponse) {
            sessionStorage.setItem("utilisateur_en_cours", JSON
                .stringify(reponse));
            utilisateurEnCours = reponse;
            affichageBouttonSelonRole(utilisateurEnCours);
        },
        error: function (responseHttp) {
            if (responseHttp.status == 401) {
                $('#loginMsgDiv').addClass('alert-danger');
                $('#loginMsgDiv').removeClass('alert-primary');
                $('#loginMsgText').text(
                    'mauvais login/mot de passe');
                $('#loginMsgDiv').css('display', 'block');
            } else {
                console.log(responseHttp);
            }
        },
    });
}

function logout() {
    utilisateurEnCours = null;
    sessionStorage.removeItem("utilisateur_en_cours");
    $('#listLien').click();
    $('.etudiantTr td:not(:last-child)').off();
    $('#ajoutLien').css('display', 'none');
    $('.actionTd').css('display', 'none');
    $('#etudiantDetailDiv').css('display', 'none');
    $('#loginLien').css('display', 'block').parent().css(
        'margin-left', 'auto');
    $('#logoutLien').css('display', 'none').parent().css(
        'margin-left', '');
    $('#ajoutMsgDiv').css('display', 'none');
    $('#loginMsgDiv').css('display', 'none');
    $('#deleteMsgDiv').css('display', 'none');
}
// affichageBouttonSelonRole(utilisateurEnCours);
function affichageBouttonSelonRole(utilisateurEnCours) {
    if (!utilisateurEnCours) {
        $('#loginLien').css('display', 'block').parent().css(
            'margin-left', 'auto');
        $('#logoutLien').css('display', 'none').parent().css(
            'margin-left', '');
        $('#lien-responsable').css('display', 'none');
        $('.actionTd').css('display', 'none');
    } else {
        $('#logoutLien').css('display', 'block').parent().css(
            'margin-left', 'auto');
        $('#loginLien').css('display', 'none').parent().css(
            'margin-left', '');
        if (utilisateurEnCours.responsable == true) {
            $('.actionTd').css('display', 'block');
            $('#lien-responsable').css('display', 'block');
        } else if ( utilisateurEnCours.responsable== false ) {
            $('#ajoutLien').css('display', 'none');
            $('#lien-responsable').css('display', 'none');
        } else {
            logout();
        }
    }
}