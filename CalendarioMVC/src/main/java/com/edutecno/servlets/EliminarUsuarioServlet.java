package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.edutecno.dao.UsuarioDAO;

@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Validación de sesión
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			response.sendRedirect("login.jsp");
			return;
		}

		// Obtener el ID del usuario a eliminar
		String idUsuarioStr = request.getParameter("id");
		if (idUsuarioStr == null || idUsuarioStr.isEmpty()) {
			request.setAttribute("error", "No se proporcionó un ID de usuario válido.");
			request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
			return;
		}

		try {
			int idUsuario = Integer.parseInt(idUsuarioStr);

			// Eliminar el usuario de la base de datos
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.eliminarUsuario(idUsuario);

			// Redirigir a BuscarUsuarioServlet para mostrar la lista actualizada
			response.sendRedirect("BuscarUsuarioServlet");
		} catch (NumberFormatException e) {
			request.setAttribute("error", "ID de usuario no válido.");
			request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
		}
	}

	// Manejar la solicitud GET
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirige las solicitudes GET al método POST
		doPost(request, response);
	}
}
