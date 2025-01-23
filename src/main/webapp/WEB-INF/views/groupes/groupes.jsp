<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<c:if test="${not empty error}">
    <div class="error-message">
        ${error}
    </div>
</c:if>

	<div class="header">
	    <a href="${pageContext.request.contextPath}/index.jsp" class="button">Page d'accueil</a>
	</div>
    <title>Liste des groupes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Liste des groupes</h1>
        <table class="styled-table">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Nombre d'adhérents</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${not empty groupes}">
                    <c:forEach var="groupe" items="${groupes}">
                        <tr>
                            <td>${groupe.nom}</td>
                            <td>${groupe.description != null ? groupe.description : 'Aucune description'}</td>
                            <td>${groupe.adherents != null ? groupe.adherents.size() : 0}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/groupes?action=details&id=${groupe.id}" >Détails</a>
                                <a href="${pageContext.request.contextPath}/groupes?action=edit&id=${groupe.id}" >Modifier</a>
                                <a href="${pageContext.request.contextPath}/groupes?action=delete&id=${groupe.id}"  onclick="return confirm('Voulez-vous vraiment supprimer ce groupe ?');">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${empty groupes}">
                    <tr>
                        <td colspan="4">Aucun groupe trouvé.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <div style="text-align: center;">
            <a href="${pageContext.request.contextPath}/groupes?action=new" >Ajouter un nouveau groupe</a>
        </div>
    </div>
</body>
</html>
