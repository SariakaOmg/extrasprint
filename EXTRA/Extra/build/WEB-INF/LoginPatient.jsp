<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Connexion patient</title>
    <style>
        * { box-sizing: border-box; }
        body { margin: 0; min-height: 100vh; display: grid; place-items: center; padding: 24px; font-family: Arial, sans-serif; color: #183044; background: #f2f7f8; }
        main { width: min(100%, 420px); padding: 36px; background: #fff; border-radius: 16px; box-shadow: 0 12px 36px #17364a18; }
        h1 { margin: 0 0 8px; font-size: 1.8rem; }
        p { margin: 0 0 26px; color: #607483; }
        label { display: block; margin: 18px 0 7px; font-weight: 600; }
        input { width: 100%; padding: 12px 13px; border: 1px solid #cbd8df; border-radius: 8px; font: inherit; }
        input:focus { outline: 3px solid #1b9a8a33; border-color: #168777; }
        button { width: 100%; margin-top: 24px; padding: 13px; border: 0; border-radius: 8px; color: white; background: #168777; font: inherit; font-weight: 700; cursor: pointer; }
        button:hover { background: #116e62; }
        .link { margin: 20px 0 0; text-align: center; font-size: .95rem; }
        a { color: #127c70; }
    </style>
</head>
<body>
<main>
    <h1>Connexion</h1>
    <p>Accédez à votre espace patient.</p>
    <form action="${pageContext.request.contextPath}/login" method="get" accept-charset="UTF-8">
        <label for="email">Adresse e-mail</label>
        <input type="email" id="email" name="email_patient" autocomplete="email" required>

        <label for="motDePasse">Mot de passe</label>
        <input type="password" id="motDePasse" name="MotdePasse_patient" autocomplete="current-password" required>

        <button type="submit">Se connecter</button>
    </form>
    <p class="link">Vous n’avez pas encore de compte ? <a href="${pageContext.request.contextPath}/SignInPatient.jsp">Créer un compte</a></p>
</main>
</body>
</html>
