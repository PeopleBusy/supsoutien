var app = angular.module("MyApp", ['ngCookies']);

var url = 'http://supsoutien.sprnantes.tk/supsoutien/api';

function afficherLoadingModal() {
    $('#loading-modal-sm').modal({
        show: true,
        backdrop: 'static'
    });
}

function fermerLoadingModal() {
    $('#loading-modal-sm').modal('hide');
}

function afficherSuccessModal() {
    $('#success-modal-sm').modal('show');
}

function fermerSuccessModal() {
    $('#success-modal-sm').modal('hide');
}

function reloadPage() {
    location.reload();
}

String.prototype.toDate = function (format)
{
    var normalized = this.replace(/[^a-zA-Z0-9]/g, '-');
    var normalizedFormat = format.toLowerCase().replace(/[^a-zA-Z0-9]/g, '-');
    var formatItems = normalizedFormat.split('-');
    var dateItems = normalized.split('-');

    var monthIndex = formatItems.indexOf("mm");
    var dayIndex = formatItems.indexOf("dd");
    var yearIndex = formatItems.indexOf("yyyy");
    var hourIndex = formatItems.indexOf("hh");
    var minutesIndex = formatItems.indexOf("ii");
    var secondsIndex = formatItems.indexOf("ss");

    var today = new Date();

    var year = yearIndex > -1 ? dateItems[yearIndex] : today.getFullYear();
    var month = monthIndex > -1 ? dateItems[monthIndex] - 1 : today.getMonth() - 1;
    var day = dayIndex > -1 ? dateItems[dayIndex] : today.getDate();

    var hour = hourIndex > -1 ? dateItems[hourIndex] : today.getHours();
    var minute = minutesIndex > -1 ? dateItems[minutesIndex] : today.getMinutes();
    //var second  = secondsIndex>-1   ? dateItems[secondsIndex] : today.getSeconds();

    return new Date(year, month, day, hour, minute);
};

app.run(['$rootScope', function ($rootScope) {
        $rootScope.page = {
            setTitle: function (title) {
                this.title = title;
            }
        };

        $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
            $rootScope.page.setTitle(current.$$route.title || 'Default Title');
        });
    }]);

app.controller("registerController", function ($scope, $http, $cookieStore, $location) {
    $scope.user = {};
    $scope.promotions = null;
    $scope.typeUserBtnEtudiantClass = "btn-default";
    $scope.typeUserBtnCoachClass = "btn-default";
    $scope.typeUser = ""; //e = etudiant, c = coach, s = staff.
    $scope.error = false;
    $scope.errormsg = "";
    $scope.successmsg = "";
    
    url = 'http://supsoutien.sprnantes.tk/supsoutien/api';

    $scope.getAllPromotions = function () {
        $http.get(url + "/promotions")
                .success(function (data) {
                    $scope.promotions = data;
                })
                .error(function (error) {
                    console.log(error);
                });
    };
    $scope.getAllPromotions();

    $scope.setEmail = function () {
        if (typeof $scope.user.idBooster === "undefined")
            $scope.user.email = "";
        else
            $scope.user.email = $scope.user.idBooster + "@supinfo.com";

    };

    $scope.selectTypeUserEtudiant = function () {
        $scope.typeUser = "e";
        $scope.typeUserBtnEtudiantClass = "btn-primary";
        $scope.typeUserBtnCoachClass = "btn-default";
    };

    $scope.selectTypeUserCoach = function () {
        $scope.typeUser = "c";
        $scope.typeUserBtnCoachClass = "btn-primary";
        $scope.typeUserBtnEtudiantClass = "btn-default";
    };

    $scope.setTypeUserToStaffBeforeRegisterUser = function () {
        $scope.typeUser = "s";
        $scope.registerUser();
    };

    $scope.fermerSuccessModal = function () {
        fermerSuccessModal();
        window.location.href = 'http://supsoutien.sprnantes.tk/supsoutien/login';
    };

    $scope.registerUser = function () {
        if ($scope.user.motPasse === "" || $scope.user.motPasse !== $scope.user.confpass) {
            $scope.error = true;
            $scope.errormsg = "Les mots de passes sont differents!";

        } else if ($scope.typeUser === "") {
            $scope.error = true;
            $scope.errormsg = "Veuillez selectionner Etudiant ou Coach!";

        } else {
            //A faire apres: Verifier d'abord si tous les champs sont renseignes

            afficherLoadingModal();

            $scope.error = false;
            $scope.user.email = $scope.user.idBooster + "@supinfo.com";

            if ($scope.typeUser === "e") {
                $http.post(url + "/users/creerCompteEtudiant/" + $scope.user.promotiomId, $scope.user)
                        .success(function (data) {
                            console.log(data);
                            if (data.length === 0) {
                                $scope.error = true;
                                $scope.errormsg = "Un compte existe déja avec cet l'Id booster: " + $scope.user.idBooster;
                                fermerLoadingModal();

                            } else {
                                fermerLoadingModal();
                                $scope.successmsg = "Veuillez activer votre compte à l'adresse mail: " + $scope.user.email;
                                afficherSuccessModal();

                                $scope.user = {};
                                $scope.typeUserBtnEtudiantClass = "btn-default";
                                $scope.typeUserBtnCoachClass = "btn-default";
                                $scope.typeUser = ""; //e = etudiant, c = coach.
                                $scope.error = false;
                                $scope.errormsg = "";

                            }

                        })
                        .error(function (error) {
                            console.log(error);
                            fermerLoadingModal();
                            $scope.error = true;
                            $scope.errormsg = error.message;
                        });
            } else if ($scope.typeUser === "c") {
                $http.post(url + "/users/creerCompteCoach/" + $scope.user.promotiomId, $scope.user)
                        .success(function (data) {
                            console.log(data);
                            if (data.length === 0) {
                                $scope.error = true;
                                $scope.errormsg = "Un compte existe déja avec cet l'Id booster: " + $scope.user.idBooster;
                                fermerLoadingModal();

                            } else {
                                fermerLoadingModal();
                                $scope.successmsg = "Veuillez activer votre compte à l'adresse mail: " + $scope.user.email;
                                afficherSuccessModal();

                                $scope.user = {};
                                $scope.typeUserBtnEtudiantClass = "btn-default";
                                $scope.typeUserBtnCoachClass = "btn-default";
                                $scope.typeUser = ""; //e = etudiant, c = coach.
                                $scope.error = false;
                                $scope.errormsg = "";

                            }

                        })
                        .error(function (error) {
                            console.log(error);
                            fermerLoadingModal();
                            $scope.error = true;
                            $scope.errormsg = error.message;
                        });
            } else {
                $http.post(url + "/users/creerCompteStaff", $scope.user)
                        .success(function (data) {
                            console.log(data);
                            if (data.length === 0) {
                                $scope.error = true;
                                $scope.errormsg = "Un compte existe déja avec cet l'Id booster: " + $scope.user.idBooster;
                                fermerLoadingModal();

                            } else {
                                fermerLoadingModal();
                                $scope.successmsg = "Veuillez activer votre compte à l'adresse mail: " + $scope.user.email;
                                afficherSuccessModal();

                                $scope.user = {};
                                $scope.typeUserBtnEtudiantClass = "btn-default";
                                $scope.typeUserBtnCoachClass = "btn-default";
                                $scope.typeUser = "s"; //e = etudiant, c = coach., s = staff
                                $scope.error = false;
                                $scope.errormsg = "";

                            }

                        })
                        .error(function (error) {
                            console.log(error);
                            fermerLoadingModal();
                            $scope.error = true;
                            $scope.errormsg = error.message;
                        });

            }

        }
    };
});

app.controller("loggedUserController", function ($scope, $http, $cookieStore, $location) {
    
    url = 'http://supsoutien.sprnantes.tk/supsoutien/api';
    
    $scope.loggeduser = {};

    $scope.getLoggedUser = function () {
        $http.get(url + "/users/getLoggedUser")
                .success(function (data) {
                    $scope.loggeduser = data;
                    localStorage.clear();
                    localStorage.setItem("id", $scope.loggeduser.id);
                    //$cookieStore.remove("id");
                    //$cookieStore.put("id", $scope.loggeduser.id);
                })
                .error(function (error) {
                    console.log(error);
                });
    };
    $scope.getLoggedUser();

});

app.controller("listUsersController", function ($scope, $http, $cookieStore) {
    $scope.pageUsers = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.idBooster = null;
    $scope.error = false;
    $scope.error2 = false;
    $scope.errormsg = "";
    $scope.confirmmsg = "";
    $scope.u = {};

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.afficherUsers = function () {
        afficherLoadingModal();

        $http.get(url + "/users?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageUsers = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherUsers();

    $scope.rechercherUserByIdBooster = function () {
        afficherLoadingModal();

        $http.get(url + "/users/rechercherUserByIdBooster/" + $scope.idBooster + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    if (data.content.length === 0) {
                        $scope.error2 = true;
                        $scope.errormsg = "Aucun compte n'existe avec l'Id booster " + $scope.idBooster + "!";
                    } else {
                        $scope.error2 = false;
                        $scope.pageUsers = data;
                        $scope.pages = new Array(data.totalPages);
                    }

                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();

    };

    $scope.actualiserListe = function () {
        $scope.idBooster = null;
        $scope.afficherUsers();
    };

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherUsers();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherUsers();
    };

    $scope.afficherConfirmationModal = function (u) {
        $scope.u = u;
        $scope.confirmmsg = "Voulez-vous vraiment ";
        if (u.etat === true) {
            $scope.confirmmsg += "désactiver";
        } else {
            $scope.confirmmsg += "activer";
        }
        $scope.confirmmsg += " l'id booster " + u.idBooster + " ?";
        $('#confirm-modal-sm').modal('show');
    };

    $scope.fermerConfirmationModal = function () {
        $('#confirm-modal-sm').modal('hide');
    };

    $scope.activerOuDesactiverUser = function () {
        $scope.fermerConfirmationModal();
        afficherLoadingModal();
        $http({
            method: 'PUT',
            url: url + "/users/activerOuDesactiverUser",
            data: $scope.u.id,
            dataType: 'json',
            contentType: "application/json"
        })
                .success(function (data) {
                    console.log(data);
                    $scope.error = true;
                    if (data.etat) {
                        $scope.errormsg = "Id booster " + data.idBooster + " activé avec succès!";
                    } else {
                        $scope.errormsg = "Id booster " + data.idBooster + " désactivé avec succès!";
                    }
                    $scope.actualiserListe();


                })
                .error(function (error) {
                    console.log(error);
                });

    };
});


app.controller("coachCreneauController", function ($scope, $http, $cookieStore) {
    $scope.creneauMatiereCoachMetier = {};
    $scope.promotions = null;
    $scope.matieres = null;
    $scope.filterPromotionCondition = {
        promotion: {
            id: 1
        }
    };
    $scope.filterMatiereCondition = {
        matiere: {
            id: 1
        }
    };

    $scope.data = {
        availableCreneaux: [
            {id: 1, name: "1h"},
            {id: 2, name: "2h"},
            {id: 3, name: "3h"},
            {id: 4, name: "4h"}

        ],
        selectedCreneau: {id: 1, name: "1h"} //This sets the default value of the select in the ui
    };

    $scope.getPromotionsInferieur = function () {
        $http.get(url + "/promotions/findPromotionsInferieures/" + localStorage.getItem("id"))
                .success(function (data) {
                    //console.log(data);
                    $scope.promotions = data;

                    $scope.filterPromotionCondition = {
                        promotion: $scope.promotions[0]
                    };
                })
                .error(function (error) {
                    console.log(error);
                });
    };
    $scope.getPromotionsInferieur();

    $scope.getMatieresByPromotion = function () {
        $http.get(url + "/matieres/findMatieresByPromotion/" + $scope.filterPromotionCondition.promotion.id)
                .success(function (data) {
                    console.log(data);
                    $scope.matieres = data;
                    $scope.filterMatiereCondition = {
                        matiere: $scope.matieres[0]
                    };

                })
                .error(function (error) {
                    console.log(error);
                });
    };
    $scope.getMatieresByPromotion();

    $scope.onPromotionChange = function () {
        $scope.getMatieresByPromotion();
    };

    $scope.registerCreneauByCoach = function () {
        afficherLoadingModal();

        $scope.creneauMatiereCoachMetier.matiereId = {
            id: parseInt($scope.filterMatiereCondition.matiere.id)
        };
        var datedeb = $('input#datedeb').val().toDate("dd/mm/yyyy hh:ii");

        var datefin = $('input#datedeb').val().toDate("dd/mm/yyyy hh:ii");

        console.log(datedeb);
        datefin.setHours(datefin.getHours() + parseInt($scope.data.selectedCreneau.id));
        console.log(datefin);

        $scope.creneauMatiereCoachMetier.dateHeureDebutSoutien = datedeb;
        $scope.creneauMatiereCoachMetier.dateHeureFinSoutien = datefin;

        $http.post(url + "/creneaumatierecoach/" + localStorage.getItem("id"), $scope.creneauMatiereCoachMetier)
                .success(function (data) {
                    console.log(data);
                    if (data.length === 0) {
                        $scope.error = true;
                        $scope.errormsg = "Vous avez déja créé un soutien pour ce créneau!";
                        fermerLoadingModal();

                    } else {
                        fermerLoadingModal();
                        afficherSuccessModal();

                        $scope.error = false;
                        $scope.errormsg = "";
                        $('input#datedeb').val('');
                        $('input#datefin').val('');
                        $scope.getPromotionsInferieur();
                        $scope.getMatieresByPromotion();

                    }

                })
                .error(function (error) {
                    console.log(error);
                    fermerLoadingModal();
                    $scope.error = true;
                    $scope.errormsg = error.message;
                });
    };

});

app.controller("listCoachCreneauxController", function ($scope, $http, $cookieStore) {
    $scope.pageCreneaux = null;
    $scope.pageInscriptions = null;
    $scope.commentaire = null;
    $scope.nbPresents = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.error = false;
    $scope.error2 = false;
    $scope.errormsg = "";
    $scope.confirmmsg = "";

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.afficherCreneaux = function () {
        afficherLoadingModal();

        $http.get(url + "/creneaumatierecoach/findCreneauxMatiereCoachById/" + localStorage.getItem("id") + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageCreneaux = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherCreneaux();

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherCreneaux();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherCreneaux();
    };

    $scope.afficherAnnulerBtn = function (c) {
        return c.validerParCoachFinSession === 0 && c.validerParStaff === 1;
    };

    $scope.afficherAnnulerModal = function (c) {
        $scope.c = c;
        $scope.confirmmsg = "Voulez-vous vraiment annuler la séance " + c.matiereId.codeMatiere + "?";
        $('#annuler-modal-sm').modal('show');
    };
    $scope.fermerAnnulerModal = function (c) {
        $('#annuler-modal-sm').modal('hide');
    };

    $scope.afficherConfirmationModal = function (c) {
        $scope.c = c;
        $scope.confirmmsg = "Voulez-vous vraiment valider la fin de session de soutien " + c.matiereId.codeMatiere + "?";
        $('#confirm-modal-sm').modal('show');
    };

    $scope.afficherInscritModal = function (c) {
        $scope.c = c;
        afficherLoadingModal();
        $scope.confirmmsg = "Liste des étudiants inscrits à la séance " + c.matiereId.codeMatiere;
        $http.get(url + "/inscriptionetudiantcreneau/findInsriptionsEtudiantsByCreneau/" + c.id)
                .success(function (data) {
                    console.log(data);
                    $scope.pageInscriptions = data;
                    $('#inscrit-modal-sm').modal('show');
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.fermInscritModal = function () {
        $('#inscrit-modal-sm').modal('hide');
    };

    $scope.confirmerSeance = function (i) {
        afficherLoadingModal();
        $http({
            method: 'PUT',
            url: url + "/inscriptionetudiantcreneau/updateInsriptionCreneauxMatiere/" + i.id + "/1",
            data: i,
            dataType: 'json',
            contentType: "application/json"
        })
                .success(function (data) {
                    console.log(data);
                    $scope.error2 = true;
                    $scope.errormsg = "La séance " + data.creneauId.matiereId.codeMatiere + " a été confirmée pour ";
                    $scope.errormsg += "l'étudiant " + data.etudiantId.nom + " " + data.etudiantId.prenom + " avec succès!";

                    $scope.afficherCreneaux();


                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
        $scope.fermInscritModal();
    };

    $scope.annulerCreneau = function () {
        afficherLoadingModal();

        $scope.c.commentaire = $scope.commentaire;
        $http({
            method: 'PUT',
            url: url + "/creneaumatierecoach/validerOuAnnlerCreneauMatiereCoachFinSession/2/" + $scope.c.nbPresents,
            data: $scope.c,
            dataType: 'json',
            contentType: "application/json"
        })
                .success(function (data) {
                    console.log(data);
                    $scope.error2 = true;
                    $scope.errormsg = "La séance " + data.matiereId.codeMatiere + " a été annulée avec succès!";

                    $scope.afficherCreneaux();

                })
                .error(function (error) {
                    console.log(error);
                });

        $scope.fermerAnnulerModal();
    };

    $scope.validerSeanceFinSession = function () {
        console.log($scope.nbPresents);
        if (typeof $scope.nbPresents === "undefined" || $scope.nbPresents === null || parseInt($scope.nbPresents) === 0) {
            $scope.error2 = false;
            $scope.error = true;
            $scope.errormsg = "Veuillez saisir le nombre d'étudiants présents!";
            $('#confirm-modal-sm').modal('hide');

        } else if(parseInt($scope.nbPresents) > parseInt($scope.c.nbInscrits)){
            $scope.error2 = false;
            $scope.error = true;
            $scope.errormsg = "Veuillez saisir un nombre d'étudiants présents inferieur au nombre d'étudiants inscrits!";
            $('#confirm-modal-sm').modal('hide');
            
        }else {
            
            $scope.error = false;
            $scope.errormsg = null;
            
            afficherLoadingModal();

            $scope.c.nbPresents = $scope.nbPresents;
            $http({
                method: 'PUT',
                url: url + "/creneaumatierecoach/validerOuAnnlerCreneauMatiereCoachFinSession/1/" + $scope.nbPresents,
                data: $scope.c,
                dataType: 'json',
                contentType: "application/json"
            })
                    .success(function (data) {
                        console.log(data);
                        $scope.error2 = true;
                        $scope.errormsg = "La fin de session " + data.matiereId.codeMatiere + " a été validée avec succès!";

                        $scope.afficherCreneaux();

                    })
                    .error(function (error) {
                        console.log(error);
                    });

            $('#confirm-modal-sm').modal('hide');
        }
    };
});


app.controller("listSoutiensCoachController", function ($scope, $http, $cookieStore) {
    $scope.pageCreneaux = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.error = false;
    $scope.error2 = false;
    $scope.errormsg = "";
    $scope.confirmmsg = "";
    $scope.valide = 0;

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.action = {
        availableOptions: [
            {id: 3, name: "Afficher coachings"},
            {id: 0, name: "Afficher coachings à valider"},
            {id: 1, name: "Afficher coachings validés"},
            {id: 2, name: "Afficher coachings rejetés"}
        ],
        selectedOption: {id: 3, name: "Afficher coachings"} //This sets the default value of the select in the ui
    };

    $scope.afficherAnnulerBtn = function (c) {
        return c.validerParCoachFinSession === 0 && c.validerParStaff === 1;
    };

    $scope.afficherAllSoutiens = function () {
        afficherLoadingModal();

        $http.get(url + "/creneaumatierecoach/findAllSoutiensMatiereCoach?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageCreneaux = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherAllSoutiens();

    $scope.afficherCreneaux = function () {
        afficherLoadingModal();

        $http.get(url + "/creneaumatierecoach/findSoutiensMatiereCoach/" + $scope.valide + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageCreneaux = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherCreneaux();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherCreneaux();
    };

    $scope.displaySoutiensElements = function () {
        $scope.error = false;
        if (parseInt($scope.action.selectedOption.id) === 3) {
            $scope.afficherAllSoutiens();
        } else {
            $scope.valide = $scope.action.selectedOption.id;
            $scope.afficherCreneaux();
        }
    };

    $scope.afficherConfirmationModal = function (c) {
        $scope.c = c;
        $scope.confirmmsg = "Voulez-vous vraiment ";
        if (c.validerParStaff !== 1) {
            $scope.confirmmsg += "valider";
        } else {
            $scope.confirmmsg += "annuler";
        }
        $scope.confirmmsg += " la matière " + c.matiereId.codeMatiere + " pour " + c.coachId.nom + " " + c.coachId.prenom + " ?";
        $('#confirm-modal-sm').modal('show');
    };

    $scope.fermerConfirmationModal = function () {
        $('#confirm-modal-sm').modal('hide');
    };

    $scope.actualiserListe = function () {
        $scope.afficherAllSoutiens();
    };

    $scope.validerOuRejetterMatiere = function () {
        $scope.fermerConfirmationModal();
        afficherLoadingModal();
        $http({
            method: 'PUT',
            url: url + "/creneaumatierecoach/validerOuRejetterMatiereCoachByStaff/" + localStorage.getItem("id"),
            data: $scope.c,
            dataType: 'json',
            contentType: "application/json"
        })
                .success(function (data) {
                    console.log(data);
                    $scope.error = true;
                    if (data.validerParStaff === 1) {
                        $scope.errormsg = "Matière " + data.matiereId.codeMatiere + " validée avec succès pour ";
                    } else {
                        $scope.errormsg = "Matière " + data.matiereId.codeMatiere + " rejetée pour ";
                    }
                    $scope.errormsg += data.coachId.nom + " " + data.coachId.prenom + "!";

                    $scope.actualiserListe();


                })
                .error(function (error) {
                    console.log(error);
                });

    };

});

app.controller("listDemandesSoutiensController", function ($scope, $http, $cookieStore) {
    $scope.pageDemandes = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.etatDemande = 0;

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.demande = {
        availableOptions: [
            {id: 2, name: "Afficher toutes les demandes"},
            {id: 1, name: "Afficher demandes traiteés"},
            {id: 0, name: "Afficher demandes non traiteés"}
        ],
        selectedOption: {id: 2, name: "Afficher toutes les demandes"} //This sets the default value of the select in the ui
    };

//    $scope.afficherAnnulerBtn = function (c) {
//        return c.validerParCoachFinSession === 0 && c.validerParStaff === 1;
//    };

    $scope.afficherAllDemandes = function () {
        afficherLoadingModal();

        $http.get(url + "/demandesoutien/findAllDemandesSoutiens?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageDemandes = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherAllDemandes();

    $scope.afficherDemandes = function () {
        afficherLoadingModal();

        $http.get(url + "/demandesoutien/findDemandesSoutiensByEtat/" + $scope.etatDemande + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageDemandes = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherDemandes();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherDemandes();
    };

    $scope.displayDemandesElements = function () {
        $scope.error = false;
        if (parseInt($scope.demande.selectedOption.id) === 2) {
            $scope.afficherAllDemandes();
        } else {
            $scope.etatDemande = $scope.demande.selectedOption.id;
            $scope.afficherDemandes();
        }
    };

    $scope.afficherConfirmationModal = function (c) {
        $scope.c = c;
        $scope.confirmmsg = "Voulez-vous vraiment ";
        if (c.validerParStaff !== 1) {
            $scope.confirmmsg += "valider";
        } else {
            $scope.confirmmsg += "annuler";
        }
        $scope.confirmmsg += " la matière " + c.matiereId.codeMatiere + " pour " + c.coachId.nom + " " + c.coachId.prenom + " ?";
        $('#confirm-modal-sm').modal('show');
    };

    $scope.fermerConfirmationModal = function () {
        $('#confirm-modal-sm').modal('hide');
    };

    $scope.actualiserListe = function () {
        $scope.afficherAllDemandes();
    };

});

app.controller("listDemandesEtudiantSoutiensController", function ($scope, $http, $cookieStore) {
    $scope.pageDemandes = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.etatDemande = 0;

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.demande = {
        availableOptions: [
            {id: 2, name: "Afficher toutes les demandes"},
            {id: 1, name: "Afficher demandes traiteés"},
            {id: 0, name: "Afficher demandes non traiteés"}
        ],
        selectedOption: {id: 2, name: "Afficher toutes les demandes"} //This sets the default value of the select in the ui
    };

    $scope.afficherAllDemandes = function () {
        afficherLoadingModal();

        $http.get(url + "/demandesoutien/findDemandesSoutiensByPromotion/" + localStorage.getItem("id") + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageDemandes = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherAllDemandes();


    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherDemandes();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherDemandes();
    };

    $scope.displayDemandesElements = function () {
        $scope.error = false;
        if (parseInt($scope.demande.selectedOption.id) === 2) {
            $scope.afficherAllDemandes();
        } else {
            $scope.etatDemande = $scope.demande.selectedOption.id;
            $scope.afficherDemandes();
        }
    };

    $scope.afficherConfirmationModal = function (c) {
        $scope.c = c;
        $scope.confirmmsg = "Voulez-vous vraiment ";
        if (c.validerParStaff !== 1) {
            $scope.confirmmsg += "valider";
        } else {
            $scope.confirmmsg += "annuler";
        }
        $scope.confirmmsg += " la matière " + c.matiereId.codeMatiere + " pour " + c.coachId.nom + " " + c.coachId.prenom + " ?";
        $('#confirm-modal-sm').modal('show');
    };

    $scope.fermerConfirmationModal = function () {
        $('#confirm-modal-sm').modal('hide');
    };

    $scope.actualiserListe = function () {
        $scope.afficherAllDemandes();
    };

});

app.controller("etudiantInscriptionController", function ($scope, $http, $filter) {
    $scope.pageCreneaux = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.display = false;
    $scope.error = false;
    $scope.error2 = false;
    $scope.errormsg = "";
    $scope.confirmmsg = "";
    $scope.btnmsg = $("select#mat option:selected").text();
    $scope.matieres = null;
    $scope.idMatiere;

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };
    $scope.getMatieresByEtudiantPromotion = function () {
        afficherLoadingModal();

        $http.get(url + "/matieres/findMatieresByEtudiantPromotion/" + localStorage.getItem("id"))
                .success(function (data) {
                    console.log(data);
                    $scope.matieres = data;
                })
                .error(function (error) {
                    console.log(error);
                });
        fermerLoadingModal();
    };
    $scope.getMatieresByEtudiantPromotion();

    $scope.getAllCreneauxMatiereDisponibleByPromotion = function () {
        afficherLoadingModal();

        $http.get(url + "/creneaumatierecoach/findAllCreneauxMatiereDisponibleByPromotion/" + localStorage.getItem("id") + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageCreneaux = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.getAllCreneauxMatiereDisponibleByPromotion();

    $scope.actualiserPage = function () {
        reloadPage();
    };

    $scope.onMatiereChange = function () {
        $scope.btnmsg = $("select#mat option:selected").text().split(" - ")[0];
        //$scope.displayDemandeBtn();
        $scope.afficherCreneaux();
    };

    $scope.afficherCreneauxAsDemande = function () {
        $http.get(url + "/creneaumatierecoach/findCreneauxDisponiblesByMatiereAsDeamnde/" + $scope.idMatiere + "/" + localStorage.getItem("id") + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageCreneaux = data;
                    $scope.pages = new Array(data.totalPages);
                    if (data.length > 0) {
                         $scope.display = false;
                    }else{
                         $scope.display = true;
                    }

                })
                .error(function (error) {
                    console.log(error);
                });
    };

    $scope.afficherCreneaux = function () {
        afficherLoadingModal();
        $http.get(url + "/creneaumatierecoach/findCreneauxDisponiblesByMatiere/" + $scope.idMatiere + "/" + localStorage.getItem("id") + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    if (data.length > 0) {
                        $scope.pageCreneaux = data;
                        $scope.pages = new Array(data.totalPages);
                        $scope.display = false;
                    } else {
                        $scope.pageCreneaux = data;
                        $scope.display = true;
                        //$scope.afficherCreneauxAsDemande();
                    }
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherCreneaux();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherCreneaux();
    };

    $scope.afficherConfirmationModal = function (c) {
        $scope.c = c;
        $scope.confirmmsg = "Voulez-vous vraiment vous inscrire au soutien " + c.matiereId.codeMatiere + " du ";
        $scope.confirmmsg2 = $filter('date')(c.dateHeureDebutSoutien, 'dd/MM/yyyy HH:mm:ss') + " - au - " +
                $filter('date')(c.dateHeureFinSoutien, 'dd/MM/yyyy HH:mm:ss') + " ?";
        $('#confirm-modal-sm').modal('show');
    };

    $scope.afficherDemandeModal = function () {

        $scope.confirmmsg = "Voulez-vous vraiment vous faire une demande de soutien pour la matière " + $("select#mat option:selected").text() + " ?";
        $('#demande-modal-sm').modal('show');

    };

    $scope.displayDemandeBtn = function () {
        if (typeof $scope.idMatiere === 'undefined' || $scope.idMatiere === null) {
            return false;
        } else {
            //afficherLoadingModal();
            //console.log(url + "/demandesoutien/findDemandeSoutiensEncoursByEtudiantMatiere/" + $scope.idMatiere + "/" + localStorage.getItem("id"));
            $http.get(url + "/demandesoutien/findDemandeSoutiensEncoursByEtudiantMatiere/" + $scope.idMatiere + "/" + localStorage.getItem("id"))
                    .success(function (data) {
                        console.log(data);
                        $scope.display = data;
                    })
                    .error(function (error) {
                        console.log(error);
                    });

            //fermerLoadingModal();
        }


    };

    $scope.fermerConfirmationModal = function () {
        $('#confirm-modal-sm').modal('hide');
    };

    $scope.fermerDemandeModal = function () {
        $('#demande-modal-sm').modal('hide');
    };

    $scope.enregistrerDemandeSoutien = function () {
        $scope.fermerDemandeModal();
        afficherLoadingModal();

        $http({
            method: 'POST',
            url: url + "/demandesoutien/saveDemandeSoutien/" + localStorage.getItem("id") + "/" + $scope.idMatiere,
            data: $scope.c,
            dataType: 'json',
            contentType: "application/json"
        })
                .success(function (data) {
                    console.log(data);
                    $scope.error2 = true;
                    $scope.errormsg = "Votre demande pour la séance de soutien " + data.matiereId.codeMatiere + " a été enregistrée avec succès!";
                    $scope.display = false;
                    $scope.getAllCreneauxMatiereDisponibleByPromotion();


                })
                .error(function (error) {
                    console.log(error);
                    fermerLoadingModal();
                });
    };

    $scope.actualiserListe = function () {
        $scope.afficherCreneaux();
    };

    $scope.inscriptionEtudiantCreneau = function () {
        $scope.fermerConfirmationModal();
        afficherLoadingModal();
        $http({
            method: 'POST',
            url: url + "/inscriptionetudiantcreneau/saveInsriptionCreneauxMatiere/" + localStorage.getItem("id"),
            data: $scope.c,
            dataType: 'json',
            contentType: "application/json"
        })
                .success(function (data) {
                    console.log(data);
                    $scope.error2 = true;
                    $scope.errormsg = "Inscription au soutien " + data.creneauId.matiereId.codeMatiere + " du " +
                            $filter('date')($scope.c.dateHeureDebutSoutien, 'dd/MM/yyyy HH:mm:ss') + " - au - " +
                            $filter('date')($scope.c.dateHeureFinSoutien, 'dd/MM/yyyy HH:mm:ss') + " effectuée avec succès!";
                    $scope.idMatiere = $scope.c.matiereId.id;
                    $scope.actualiserListe();


                })
                .error(function (error) {
                    console.log(error);
                    fermerLoadingModal();
                });

    };

});

app.controller("listInscriptionEtudiantController", function ($scope, $http, $cookieStore) {
    $scope.pageInscriptions = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.error = false;
    $scope.error2 = false;
    $scope.errormsg = "";
    $scope.confirmmsg = "";

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.afficherInscriptions = function () {
        afficherLoadingModal();

        $http.get(url + "/inscriptionetudiantcreneau/findInsriptionCreneauxMatiereByEtudiant/" + localStorage.getItem("id") + "?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageInscriptions = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherInscriptions();

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherInscriptions();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherInscriptions();
    };

});

app.controller("listAllInscriptionsController", function ($scope, $http, $sce, $filter) {
    $scope.pageInscriptions = null;
    $scope.pageCreneaux = null;
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.error = false;
    $scope.error2 = false;
    $scope.errormsg = "";
    $scope.confirmmsg = "";

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.afficherInscriptions = function () {
        afficherLoadingModal();

        //$http.get(url + "/inscriptionetudiantcreneau/findAllInsriptionCreneauxMatiere?page=" + $scope.page + "&size=" + $scope.size)
        $http.get(url + "/creneaumatierecoach/findAllSoutiensMatiereCoach?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    $scope.pageCreneaux = data;
                    console.log($scope.pageCreneaux);
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherInscriptions();

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherInscriptions();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherInscriptions();
    };
    
    $scope.afficherInscritModal = function (c) {
        $scope.c = c;
        afficherLoadingModal();
        
        $scope.title = " à la séance de soutien " + c.matiereId.codeMatiere + " du ";
        $scope.title += $filter('date')(c.dateHeureDebutSoutien, 'dd/MM/yyyy HH:mm:ss') + " - au - " +
                $filter('date')(c.dateHeureFinSoutien, 'dd/MM/yyyy HH:mm:ss');
        
        $http.get(url + "/inscriptionetudiantcreneau/findInsriptionsEtudiantsByCreneau/" + c.id)
                .success(function (data) {
                    console.log(data);
                    $scope.pageInscriptions = data;
                    $('#inscrit-modal-sm').modal('show');
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.fermInscritModal = function () {
        $('#inscrit-modal-sm').modal('hide');
    };

    $scope.convertToHtml = function (s) {
        return $sce.trustAsHtml(s);
    };

    $scope.showRowClass = function (c) {
        if (c.validerParCoachFinSession === 2) {
            return 'danger';
        } else if (c.validerParCoachFinSession === 1) {
            return 'success';
        } else {
            return '';
        }
    };

});

app.controller("listMatiereController", function ($scope, $http, $cookieStore) {
    $scope.pageMatieres = null;
    $scope.promotions = null;
    $scope.filterPromotionCondition = {
        promotion: {
            id: 1
        }
    };
    $scope.matiere = {};
    $scope.page = 0;
    $scope.size = 5;
    $scope.pages = [];
    $scope.sizes = [];
    $scope.idBooster = null;
    $scope.error = false;
    $scope.error2 = false;
    $scope.errormsg = "";
    $scope.confirmmsg = "";
    $scope.u = {};

    $scope.data = {
        availableOptions: [
            {id: 5, name: "Afficher 5 enregistrements"},
            {id: 10, name: "Afficher 10 enregistrements"}
        ],
        selectedOption: {id: 5, name: "Afficher 5 enregistrements"} //This sets the default value of the select in the ui
    };

    $scope.afficherMatieres = function () {
        afficherLoadingModal();

        $http.get(url + "/matieres/findAllMatieres?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageMatieres = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherMatieres();

    $scope.afficherPromotions = function () {
        afficherLoadingModal();

        $http.get(url + "/promotions")
                .success(function (data) {
                    console.log(data);
                    $scope.promotions = data;
                    $scope.filterPromotionCondition = {
                        promotion: $scope.promotions[0]
                    };
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };
    $scope.afficherPromotions();
    
    $scope.chargerMatieres = function () {
        afficherLoadingModal();

        $http.get(url + "/matieres/chargerMatieres?page=" + $scope.page + "&size=" + $scope.size)
                .success(function (data) {
                    console.log(data);
                    $scope.pageMatieres = data;
                    $scope.pages = new Array(data.totalPages);
                })
                .error(function (error) {
                    console.log(error);
                });

        fermerLoadingModal();
    };

    $scope.actualiserListe = function () {
        $scope.afficherMatieres();
    };

    $scope.goToPage = function (index) {
        $scope.page = index;
        $scope.afficherMatieres();
    };

    $scope.displayElements = function () {
        $scope.page = 0;
        $scope.size = $scope.data.selectedOption.id;
        $scope.afficherMatieres();
    };

    $scope.afficherConfirmationModal = function () {
        $scope.confirmmsg = "Voulez-vous vraiment enregistrer cette matière?";
        $('#confirm-modal-sm').modal('show');
    };

    $scope.fermerConfirmationModal = function () {
        $('#confirm-modal-sm').modal('hide');
    };

    $scope.recupererMatiere = function (m) {
        $scope.matiere = m;
        $scope.filterPromotionCondition = {
            promotion: $scope.promotions[parseInt(m.promotionId.id) - 1]
        };
    };

    $scope.saveOrUpdateMatiere = function () {
        $scope.fermerConfirmationModal();
        afficherLoadingModal();

        $scope.matiere.promotionId = $scope.filterPromotionCondition.promotion;

        $http({
            method: 'POST',
            url: url + "/matieres",
            data: $scope.matiere,
            dataType: 'json',
            contentType: "application/json"
        })
                .success(function (data) {
                    console.log(data);
                    $scope.error2 = false;
                    $scope.error = true;
                    $scope.errormsg = "Enrégistrement effectué avec succès!";
                    $scope.matiere = {};
                    $scope.actualiserListe();
                    fermerLoadingModal();


                })
                .error(function (error) {
                    console.log(error);
                    fermerLoadingModal();
                    $scope.error = false;
                    $scope.error2 = true;
                    $scope.errormsg = error.message;
                });


    };

});

