<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Inscription patient</title>
    <style>
        * { box-sizing: border-box; }
        body { margin: 0; min-height: 100vh; display: grid; place-items: center; padding: 24px; font-family: Arial, sans-serif; color: #183044; background: #f2f7f8; }
        main { width: min(100%, 500px); padding: 36px; background: #fff; border-radius: 16px; box-shadow: 0 12px 36px #17364a18; }
        h1 { margin: 0 0 8px; font-size: 1.8rem; }
        p { margin: 0 0 24px; color: #607483; }
        .fields { display: grid; grid-template-columns: 1fr 1fr; gap: 0 16px; }
        label { display: block; margin: 16px 0 7px; font-weight: 600; }
        input { width: 100%; padding: 12px 13px; border: 1px solid #cbd8df; border-radius: 8px; font: inherit; }
        input:focus { outline: 3px solid #1b9a8a33; border-color: #168777; }
        button { width: 100%; margin-top: 24px; padding: 13px; border: 0; border-radius: 8px; color: white; background: #168777; font: inherit; font-weight: 700; cursor: pointer; }
        button:hover { background: #116e62; }
        .link { margin: 20px 0 0; text-align: center; font-size: .95rem; }
        a { color: #127c70; }
        @media (max-width: 480px) { main { padding: 26px 22px; } .fields { grid-template-columns: 1fr; } }
    </style>
</head>
<body>
<main>
    <h1>Créer un compte</h1>
    <p>Renseignez vos informations pour rejoindre votre espace patient.</p>
    <form action="${pageContext.request.contextPath}/inscription" method="get" accept-charset="UTF-8">
        <div class="fields">
            <div>
                <label for="nom">Nom</label>
                <input type="text" id="nom" name="Nom_patient" autocomplete="family-name" required>
            </div>
            <div>
                <label for="prenom">Prénom</label>
                <input type="text" id="prenom" name="Prenom_patient" autocomplete="given-name" required>
            </div>
        </div>

        <label for="email">Adresse e-mail</label>
        <input type="email" id="email" name="email_patient" autocomplete="email" required>

        <div class="fields">
            <div>
                <label for="age">Âge</label>
                <input type="number" id="age" name="Age_patient" min="0" max="150" required>
            </div>
            <div>
                <label for="dateNaissance">Date de naissance</label>
                <input type="date" id="dateNaissance" name="DateNaissance_patient">
            </div>
        </div>

        <label for="motDePasse">Mot de passe</label>
        <input type="password" id="motDePasse" name="MotdePasse_patient" autocomplete="new-password" minlength="8" required>

        <button type="submit">Créer mon compte</button>
    </form>
    <p class="link">Vous avez déjà un compte ? <a href="${pageContext.request.contextPath}/login">Se connecter</a></p>
</main>
</body>
</html>
