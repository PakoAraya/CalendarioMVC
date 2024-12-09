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
            <p>Tu signo del horóscopo chino es: <strong><%= horoscopo.getAnimal() %></strong></p>
            <p>Válido desde: <%= horoscopo.getFechaInicio() %></p>
            <p>Hasta: <%= horoscopo.getFechaFin() %></p>
        <% } else if (request.getAttribute("error") != null) { %>
            <div class="error-message">
                <p><%= request.getAttribute("error") %></p>
            </div>
        <% } %>
    </main>
</body>
</html>
