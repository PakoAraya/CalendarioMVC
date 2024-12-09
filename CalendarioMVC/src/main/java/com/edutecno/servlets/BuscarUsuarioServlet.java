package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

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
		
		int idUsuario = Integer.parseInt(request.getParameter("id"));
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO usuarioDTO = usuarioDAO.traerUsuarioPorId(idUsuario);

        request.setAttribute("usuario", usuarioDTO);
        request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
	}
}
