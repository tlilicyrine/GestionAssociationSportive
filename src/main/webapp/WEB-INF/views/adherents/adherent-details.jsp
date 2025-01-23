<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<div class="header">
	    <a href="${pageContext.request.contextPath}/index.jsp" class="button">Page d'accueil</a>
	</div>
    <title>Détails Adhérent</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Détails de l'adhérent</h1>
        <p><strong>Nom :</strong> ${adherent.nom}</p>
        <p><strong>Prénom :</strong> ${adherent.prenom}</p>
        <p><strong>Date de naissance :</strong> ${adherent.dateNaissance}</p>
        <p><strong>Adresse :</strong> ${adherent.adresse}</p>
        <p><strong>Code postal :</strong> ${adherent.codePostal}</p>
        <p><strong>Ville :</strong> ${adherent.ville}</p>
        <p><strong>Email :</strong> ${adherent.email}</p>
        <p><strong>Téléphone :</strong> ${adherent.telephone}</p>
        <p><strong>Date de paiement de la cotisation :</strong> ${adherent.dateCotisation}</p>
        <p><strong>Groupe :</strong> ${adherent.groupe != null ? adherent.groupe.nom : 'Non assigné'}</p>
        <a href="${pageContext.request.contextPath}/adherents" class="button">Retour à la liste des adhérents</a>
    </div>
</body>
</html>
