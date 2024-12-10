<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Resultado del Horóscopo</title>
</head>
<body>
    <header>
        <h1>Consulta tu Horóscopo Chino</h1>
    </header>
    <main>
        <h2>Resultado del Horóscopo</h2>
        <%
            com.edutecno.dto.HoroscopoDTO horoscopo = 
                (com.edutecno.dto.HoroscopoDTO) request.getAttribute("horoscopo");
            if (horoscopo != null) {
        %>
            <div class="result">
                <h3>Tu signo del horóscopo chino es: <%= horoscopo.getAnimal() %></h3>
                <p>Válido desde: <%= horoscopo.getFechaInicio() %></p>
                <p>Hasta: <%= horoscopo.getFechaFin() %></p>
            </div>
        <% } else if (request.getAttribute("error") != null) { %>
            <div class="error-message">
                <p><%= request.getAttribute("error") %></p>
            </div>
        <% } %>

        <!-- Contenedor para los botones centrados -->
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
