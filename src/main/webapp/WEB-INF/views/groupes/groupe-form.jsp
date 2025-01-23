<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<div class="header">
	    <a href="${pageContext.request.contextPath}/index.jsp" class="button">Page d'accueil</a>
	</div>
    <title>Formulaire Groupe</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>${groupe == null || groupe.id == null ? "Ajouter" : "Modifier"} un groupe</h1>
        <form action="${pageContext.request.contextPath}/groupes" method="post" class="styled-form">
            <!-- ID caché pour la modification -->
            <input type="hidden" name="id" value="${groupe != null ? groupe.id : ''}">

            <!-- Nom -->
            <div class="form-row">
                <label>Nom :</label>
                <input type="text" name="nom" value="${groupe != null ? groupe.nom : ''}" required>
            </div>

            <!-- Description -->
            <div class="form-row">
                <label>Description :</label>
                <textarea name="description" placeholder="Description du groupe">${groupe != null ? groupe.description : ''}</textarea>
            </div>

            <!-- Boutons -->
            <div class="form-row full-width">
                <button type="submit" class="button">Enregistrer</button>
                <a href="${pageContext.request.contextPath}/groupes" class="button cancel">Annuler</a>
            </div>
        </form>
    </div>
</body>
</html>
