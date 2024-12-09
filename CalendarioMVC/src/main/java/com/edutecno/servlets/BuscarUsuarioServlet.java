package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.dto.UsuarioDTO;

/**
 * Servlet implementation class BuscarUsuarioServlet
 */
@WebServlet("/BuscarUsuarioServlet")
public class BuscarUsuarioServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Validacion de sesion y usuario administrador antes de continuar
		HttpSession session = request.getSession(false); // Obtener la sesión si existe
		if (session == null || session.getAttribute("username") == null) {
			// Si no está logueado, redirigir al Login
			response.sendRedirect("login.jsp");
			return;
		}
		
		// Obtener todos los usuarios desde el DAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        List<UsuarioDTO> usuarios = usuarioDAO.traerTodosUsuarios();

        // Pasar la lista de usuarios al JSP
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
	}
}
