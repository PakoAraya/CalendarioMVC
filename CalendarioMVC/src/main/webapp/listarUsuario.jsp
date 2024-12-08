<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Listado de Usuarios</title>
</head>
<body>
    <header>
        <h1>Consulta tu Horóscopo Chino</h1>
    </header>
    <main>
        <h2>Lista de Usuarios</h2>
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Usuario</th>
                    <th>Email</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Animal</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    java.util.List<com.edutecno.modelo.Usuario> usuarios = 
                        (java.util.List<com.edutecno.modelo.Usuario>) request.getAttribute("usuarios");
                    if (usuarios != null) {
                        for (com.edutecno.modelo.Usuario usuario : usuarios) {
                %>
                        <tr>
                            <td><%= usuario.getId() %></td>
                            <td><%= usuario.getNombre() %></td>
                            <td><%= usuario.getUsername() %></td>
                            <td><%= usuario.getEmail() %></td>
                            <td><%= usuario.getFechaNacimiento() %></td>
                            <td><%= usuario.getAnimal() %></td>
                            <td>
                                <form action="modificarUsuario.jsp" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                    <button type="submit" class="action-btn">Editar</button>
                                </form>
                                <form action="eliminarUsuario.jsp" method="get" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                    <button type="submit" class="action-btn">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="7">No hay usuarios registrados.</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </main>
</body>
</html>
