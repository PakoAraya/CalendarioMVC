package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class IniciarSesionServlet
 */
@WebServlet("/IniciarSesionServlet")
public class IniciarSesionServlet extends HttpServlet {
	//Variables de usuario y password dentro del codigo
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123456";
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		//Validar las credenciales del codigo con respecto a las recibidas por parametros
		if(USERNAME.equals(usuario) && PASSWORD.equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			response.sendRedirect("LibroServlet?action=list");
		}else {
			request.setAttribute("error", "Credenciales Invalidas");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}