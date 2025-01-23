<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<div class="header">
	    <a href="${pageContext.request.contextPath}/index.jsp" class="button">Page d'accueil</a>
	</div>
    <title>Formulaire Adhérent</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>${adherent == null || adherent.id == null ? "Ajouter" : "Modifier"} un adhérent</h1>
        <form action="${pageContext.request.contextPath}/adherents" method="post" class="styled-form">
            <input type="hidden" name="id" value="${adherent != null ? adherent.id : ''}">
            
            <div class="form-row">
                <label>Nom :</label>
                <input type="text" name="nom" value="${adherent != null ? adherent.nom : ''}" required>
            </div>
            <div class="form-row">
                <label>Prénom :</label>
                <input type="text" name="prenom" value="${adherent != null ? adherent.prenom : ''}" required>
            </div>

            <div class="form-row">
                <label>Date de naissance :</label>
                <input type="date" name="dateNaissance" value="${adherent != null ? adherent.dateNaissance : ''}" required>
            </div>
            <div class="form-row">
                <label>Email :</label>
                <input type="email" name="email" value="${adherent != null ? adherent.email : ''}" required>
            </div>

            <div class="form-row">
                <label>Adresse :</label>
                <input type="text" name="adresse" value="${adherent != null ? adherent.adresse : ''}" required>
            </div>
            <div class="form-row">
                <label>Code postal :</label>
                <input type="text" name="codePostal" value="${adherent != null ? adherent.codePostal : ''}" required>
            </div>

            <div class="form-row">
                <label>Ville :</label>
                <input type="text" name="ville" value="${adherent != null ? adherent.ville : ''}" required>
            </div>
            <div class="form-row">
                <label>Téléphone :</label>
                <input type="text" name="telephone" value="${adherent != null ? adherent.telephone : ''}" required>
            </div>

            <div class="form-row">
                <label>Date paiement de la cotisation :</label>
                <input type="date" name="dateCotisation" value="${adherent != null ? adherent.dateCotisation : ''}" required>
            </div>
            <div class="form-row">
                <label>Groupe :</label>
                <select name="groupeId" required>
                    <option value="" disabled selected>Choisissez un groupe</option>
                    <c:forEach var="groupe" items="${groupes}">
                        <option value="${groupe.id}" ${adherent != null && adherent.groupe != null && adherent.groupe.id == groupe.id ? 'selected' : ''}>${groupe.nom}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-row full-width">
                <button type="submit" class="button">Enregistrer</button>
                <a href="${pageContext.request.contextPath}/adherents" class="button cancel">Annuler</a>
            </div>
        </form>
    </div>
</body>
</html>
