<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
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
            <% 
                com.edutecno.dto.UsuarioDTO usuario = (com.edutecno.dto.UsuarioDTO) request.getAttribute("usuario");
                if (usuario != null) {
            %>
                <input type="hidden" name="id" value="<%= usuario.getId() %>">
                
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%= usuario.getNombre() %>" required>

                <label for="username">Usuario:</label>
                <input type="text" id="username" name="username" value="<%= usuario.getUsername() %>" required>

                <label for="email">Correo Electrónico:</label>
                <input type="email" id="email" name="email" value="<%= usuario.getEmail() %>" required>

                <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(usuario.getFechaNacimiento()) %>" required>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" value="<%= usuario.getPassword() %>" required>

                <button type="submit">Actualizar Usuario</button>
            <% } else { %>
                <p>No se encontró el usuario.</p>
            <% } %>
        </form>

        <div class="error-message">
            <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
        </div>
        
        <div class="action-buttons">
            <form action="CerrarSesionServlet" method="get">
                <button type="submit">Cerrar Sesión</button>
            </form>
            <form action="admin.jsp" method="get">
                <button type="submit">Volver al Menú Principal</button>
            </form>
        </div>
    </main>
</body>
</html>
