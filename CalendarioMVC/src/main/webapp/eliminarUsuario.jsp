<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Eliminar Usuario</title>
</head>
<body>
    <header>
        <h1>Consulta tu Horóscopo Chino</h1>
    </header>
    <main>
        <h2>Eliminar Usuario</h2>
        <form action="EliminarUsuarioServlet" method="post">
            <label for="id">ID del Usuario</label>
            <input type="text" id="id" name="id" placeholder="Ingresa el ID del usuario" required>

            <button type="submit">Eliminar Usuario</button>
        </form>
    </main>
</body>
</html>
