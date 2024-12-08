<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Inicio de Sesión</title>
</head>
<body>
    <header>
        <h1>Consulta tu Horóscopo Chino</h1>
    </header>
    <main>
        <h2>Iniciar Sesión</h2>
        <form action="LoginServlet" method="post">
            <label for="username">Usuario</label>
            <input type="text" id="username" name="username" placeholder="Ingresa tu usuario" required>

            <label for="password">Contraseña</label>
            <input type="password" id="password" name="password" placeholder="Ingresa tu contraseña" required>

            <button type="submit">Iniciar Sesión</button>
        </form>
        <p>¿No tienes cuenta? <a href="registro.jsp">Regístrate aquí</a>.</p>
    </main>
</body>
</html>
