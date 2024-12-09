<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/styles.css">
<title>Bienvenida Administrador</title>
</head>
<body>
	<header>
		<h1>Consulta tu Horóscopo Chino</h1>
	</header>
	<main>
		<h2>Bienvenido, Administrador</h2>
		<div class="admin-menu">
			<form action="ConsultaHoroscopoServlet" method="post">
				<button type="submit">¿Cuál es tu animal?</button>
			</form>
			<form action="BuscarUsuarioServlet" method="get">
				<button type="submit">Buscar Usuarios</button>
			</form>
			<form action="ModificarUsuarioServlet" method="post">
				<button type="submit">Modificar Datos</button>
			</form>
			<form action="EliminarUsuarioServlet" method="post">
				<button type="submit">Eliminar Cuenta</button>
			</form>
		</div>
	</main>
</body>
</html>
