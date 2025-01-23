<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil - Association Sportive</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <h1>Bienvenue sur l'application de gestion de l'Association Sportive</h1>
        <p>Utilisez les options ci-dessous pour gérer les adhérents et les groupes.</p>
        
        <div class="actions">
            <!-- Carte Adhérents -->
            <div class="card">
                <h2>Adhérents</h2>
                <p>Ajoutez, consultez ou gérez les adhérents de l'association.</p>
                <a href="${pageContext.request.contextPath}/adherents?action=new" class="button">Ajouter un Adhérent</a>
                <a href="${pageContext.request.contextPath}/adherents" class="button">Voir la Liste des Adhérents</a>
            </div>

            <!-- Carte Groupes -->
            <div class="card">
                <h2>Groupes</h2>
                <p>Ajoutez, consultez ou gérez les groupes de l'association.</p>
                <a href="${pageContext.request.contextPath}/groupes?action=new" class="button">Ajouter un Groupe</a>
                <a href="${pageContext.request.contextPath}/groupes" class="button">Voir la Liste des Groupes</a>
            </div>
        </div>
    </div>
</body>
</html>
