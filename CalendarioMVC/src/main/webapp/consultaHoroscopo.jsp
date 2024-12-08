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
        <h2>Tu Horóscopo Chino</h2>
        <form action="ConsultaHoroscopoServlet" method="post">
            <label for="fechaNacimiento">Fecha de Nacimiento</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>

            <button type="submit">Consultar</button>
        </form>

        <!-- Mostrar resultado del horóscopo -->
        <% 
            String horoscopo = (String) request.getAttribute("horoscopo");
            if (horoscopo != null) {
        %>
            <div>
                <h3>Resultado:</h3>
                <p>Tu signo del horóscopo chino es: <strong><%= horoscopo %></strong></p>
            </div>
        <% } %>
    </main>
</body>
</html>
