<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Modificar Usuario</title>
</head>
<body>
    <header>
        <h1>Consulta tu Horóscopo Chino</h1>
    </header>
    <main>
        <h2>Modificar Usuario</h2>
        <form action="ModificarUsuarioServlet" method="post">
            <label for="id">ID del Usuario</label>
            <input type="text" id="id" name="id" value="${usuario.id}" readonly>

            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" value="${usuario.nombre}" required>

            <label for="username">Usuario</label>
            <input type="text" id="username" name="username" value="${usuario.username}" required>

            <label for="email">Correo Electrónico</label>
            <input type="email" id="email" name="email" value="${usuario.email}" required>

            <label for="fechaNacimiento">Fecha de Nacimiento</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="${usuario.fechaNacimiento}" required>

            <button type="submit">Actualizar Usuario</button>
        </form>
    </main>
</body>
</html>
