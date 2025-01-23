<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<div class="header">
	    <a href="${pageContext.request.contextPath}/index.jsp" class="button">Page d'accueil</a>
	</div>
    <title>D�tails Adh�rent</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>D�tails de l'adh�rent</h1>
        <p><strong>Nom :</strong> ${adherent.nom}</p>
        <p><strong>Pr�nom :</strong> ${adherent.prenom}</p>
        <p><strong>Date de naissance :</strong> ${adherent.dateNaissance}</p>
        <p><strong>Adresse :</strong> ${adherent.adresse}</p>
        <p><strong>Code postal :</strong> ${adherent.codePostal}</p>
        <p><strong>Ville :</strong> ${adherent.ville}</p>
        <p><strong>Email :</strong> ${adherent.email}</p>
        <p><strong>T�l�phone :</strong> ${adherent.telephone}</p>
        <p><strong>Date de paiement de la cotisation :</strong> ${adherent.dateCotisation}</p>
        <p><strong>Groupe :</strong> ${adherent.groupe != null ? adherent.groupe.nom : 'Non assign�'}</p>
        <a href="${pageContext.request.contextPath}/adherents" class="button">Retour � la liste des adh�rents</a>
    </div>
</body>
</html>
