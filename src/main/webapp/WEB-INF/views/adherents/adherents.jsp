<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<div class="header">
	    <a href="${pageContext.request.contextPath}/index.jsp" class="button">Page d'accueil</a>
	</div>
    <title>Liste des Adh�rents</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Liste des adh�rents</h1>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Pr�nom</th>
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
                        <td>${adherent.groupe != null ? adherent.groupe.nom : 'Non assign�'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/adherents?action=details&id=${adherent.id}">D�tails</a>
                            <a href="${pageContext.request.contextPath}/adherents?action=edit&id=${adherent.id}">Modifier</a>
                            <a href="${pageContext.request.contextPath}/adherents?action=delete&id=${adherent.id}" onclick="return confirm('Voulez-vous vraiment supprimer cet adh�rent ?');">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty adherents}">
                <tr>
                    <td colspan="7">Aucun adh�rent trouv�.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <div style="text-align: center;">
        <a href="${pageContext.request.contextPath}/adherents?action=new" class="button">Ajouter un nouvel adh�rent</a>
    </div>
</body>
</html>
