<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>D�tails du Groupe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>D�tails du Groupe</h1>
        <p><strong>Nom :</strong> ${groupe.nom}</p>
        <p><strong>Description :</strong> ${groupe.description != null ? groupe.description : 'Aucune description'}</p>
        <p><strong>Adh�rents :</strong></p>
        <ul>
            <c:if test="${groupe.adherents != null && not empty groupe.adherents}">
                <c:forEach var="adherent" items="${groupe.adherents}">
                    <li>${adherent.nom} ${adherent.prenom}</li>
                </c:forEach>
            </c:if>
            <c:if test="${groupe.adherents == null || empty groupe.adherents}">
                <li>Aucun adh�rent dans ce groupe.</li>
            </c:if>
        </ul>
        <a href="${pageContext.request.contextPath}/groupes" class="button">Retour � la liste des groupes</a>
    </div>
</body>
</html>
