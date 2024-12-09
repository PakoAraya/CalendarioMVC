<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title>Consulta de Horóscopo</title>
</head>
<body>
    <header>
        <h1>Consulta tu Horóscopo Chino</h1>
    </header>
    <main>
        <h2>Consulta tu Horóscopo Chino</h2>
        <!-- Formulario para enviar la fecha de nacimiento -->
        <form action="ConsultaHoroscopoServlet" method="post">
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>

            <button type="submit">Consultar</button>
        </form>

        <!-- Mostrar resultado del horóscopo si está disponible -->
        <%
            // Intentar obtener el objeto HoroscopoDTO del atributo de la solicitud
            com.edutecno.dto.HoroscopoDTO horoscopo = 
                (com.edutecno.dto.HoroscopoDTO) request.getAttribute("horoscopo");
            
            // Verificar si se encontró el horóscopo
            if (horoscopo != null) {
        %>
            <div class="result">
                <h3>Resultado:</h3>
                <p>Tu signo del horóscopo chino es: <strong><%= horoscopo.getAnimal() %></strong></p>
                <p>El período de este signo es desde: <strong><%= horoscopo.getFechaInicio() %></strong> 
                hasta: <strong><%= horoscopo.getFechaFin() %></strong></p>
            </div>
        <% } else if (request.getAttribute("error") != null) { %>
            <!-- Mostrar mensaje de error si algo falló -->
            <div class="error-message">
                <p><%= request.getAttribute("error") %></p>
            </div>
        <% } %>
    </main>
</body>
</html>
