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
        <!-- Contenedor para la tabla -->
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
                                <!-- Mostrar el animal correctamente -->
                                <td><%= usuario.getHoroscopoDTO() != null ? usuario.getHoroscopoDTO().getAnimal() : "Sin horóscopo" %></td>
                                <td>
                                    <!-- Formulario para editar -->
                                    <form action="ModificarUsuarioServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                        <button type="submit" class="editar">Editar</button>
                                    </form>
                                    <!-- Formulario para eliminar -->
                                    <form action="EliminarUsuarioServlet" method="get" style="display:inline;">
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

        <!-- Contenedor para los botones centrados -->
        <div class="action-buttons">
            <!-- Cerrar sesión -->
            <form action="CerrarSesionServlet" method="get">
                <button type="submit">Cerrar Sesión</button>
            </form>
            <!-- Volver al menú principal -->
            <form action="admin.jsp" method="get">
                <button type="submit">Volver al Menú Principal</button>
            </form>
        </div>
    </main>
</body>
</html>
