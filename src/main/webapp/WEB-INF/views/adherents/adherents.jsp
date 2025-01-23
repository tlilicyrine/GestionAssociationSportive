<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<div class="header">
	    <a href="${pageContext.request.contextPath}/index.jsp" class="button">Page d'accueil</a>
	</div>
    <title>Liste des Adhérents</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Liste des adhérents</h1>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Date de Naissance</th>
                <th>Date de Cotisation</th>
                <th>Groupe</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty adherents}">
                <c:forEach var="adherent" items="${adherents}">
                    <tr>
                        <td>${adherent.nom}</td>
                        <td>${adherent.prenom}</td>
                        <td>${adherent.email}</td>
                        <td>${adherent.dateNaissance}</td>
                        <td>${adherent.dateCotisation}</td>
                        <td>${adherent.groupe != null ? adherent.groupe.nom : 'Non assigné'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/adherents?action=details&id=${adherent.id}">Détails</a>
                            <a href="${pageContext.request.contextPath}/adherents?action=edit&id=${adherent.id}">Modifier</a>
                            <a href="${pageContext.request.contextPath}/adherents?action=delete&id=${adherent.id}" onclick="return confirm('Voulez-vous vraiment supprimer cet adhérent ?');">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty adherents}">
                <tr>
                    <td colspan="7">Aucun adhérent trouvé.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/adherents?action=new" class="button">Ajouter un nouvel adhérent</a>
    </div>
</body>
</html>
