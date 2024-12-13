<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/registro-usuario.css">
    <title>Registro de Usuario</title>
</head>
<body>
    <header>
        <h1>Registro de Usuario</h1>
    </header>
    <main>
        <div class="form-container">
            <form action="CrearUsuarioServlet" method="post">
                <label for="nombre">Nombre</label>
                <input type="text" id="nombre" name="nombre" placeholder="Ingresa tu nombre completo" required>

                <label for="username">Usuario</label>
                <input type="text" id="username" name="username" placeholder="Elige un nombre de usuario" required>

                <label for="email">Correo Electrónico</label>
                <input type="email" id="email" name="email" placeholder="Ingresa tu correo electrónico" required>

                <label for="fechaNacimiento">Fecha de Nacimiento</label>
                <input type="date" id="fechaNacimiento" name="fecha_nacimiento" required>

                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" placeholder="Elige una contraseña" required>

                <button type="submit">Registrarse</button>
            </form>

            <!-- Mensaje de error si existe -->
            <% String error = (String) request.getAttribute("error"); %>
            <% if (error != null) { %>
                <div class="error-message">
                    <p><%= error %></p>
                </div>
            <% } %>

            <p>¿Ya tienes cuenta? <a href="login.jsp">Inicia sesión aquí</a>.</p>
        </div>
    </main>
</body>
</html>
