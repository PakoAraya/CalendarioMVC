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
        <h2>Lista de Usuarios</h2>
        <div class="table-container">
            <table>
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
                        java.util.List<com.edutecno.dto.UsuarioDTO> usuarios =
                            (java.util.List<com.edutecno.dto.UsuarioDTO>) request.getAttribute("usuarios");
                        if (usuarios != null && !usuarios.isEmpty()) {
                            for (com.edutecno.dto.UsuarioDTO usuario : usuarios) {
                    %>
                            <tr>
                                <td><%= usuario.getId() %></td>
                                <td><%= usuario.getNombre() %></td>
                                <td><%= usuario.getUsername() %></td>
                                <td><%= usuario.getEmail() %></td>
                                <td><%= usuario.getFechaNacimiento() %></td>
                                <td><%= usuario.getHoroscopoDTO() != null ? usuario.getHoroscopoDTO().getAnimal() : "Sin horóscopo" %></td>
                                <td>
                                    <form action="EliminarUsuarioServlet?redirigir=eliminar" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                        <button type="submit" class="eliminar">Eliminar</button>
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
        </div>

        <!-- Mensajes de error -->
        <div>
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
