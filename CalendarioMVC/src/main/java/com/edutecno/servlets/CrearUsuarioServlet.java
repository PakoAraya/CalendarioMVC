package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.dto.HoroscopoDTO;
import com.edutecno.dto.UsuarioDTO;

/**
 * Servlet implementation class CrearUsuarioServlet
 */
@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Validacion de sesion y usuario administrador antes de continuar
        HttpSession session = request.getSession(false); // Obtener la sesión si existe
        if (session == null || session.getAttribute("username") == null) {
            // Si no está logueado, redirigir al Login
            response.sendRedirect("login.jsp");
            return;
        }
        
        //Obtener los parametros del formulario para crear usuario
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String password = request.getParameter("password");
        int horoscopoId = Integer.parseInt(request.getParameter("horoscopoId"));
        
        try {
        	//Convertir fecha de nacimiento
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
			
        	//Crear UsuarioDTO
        	UsuarioDTO usuarioDTO = new UsuarioDTO();
        	usuarioDTO.setNombre(nombre);
        	usuarioDTO.setUsername(username);
        	usuarioDTO.setEmail(email);
        	usuarioDTO.setFechaNacimiento(fechaNacimiento);
        	usuarioDTO.setPassword(password);
        	usuarioDTO.setHoroscopoDTO(new HoroscopoDTO());
        	usuarioDTO.getHoroscopoDTO().setId(horoscopoId);
        	
        	//Guardar en la base de datos
        	UsuarioDAO usuarioDAO = new UsuarioDAO();
        	usuarioDAO.agregarUsuario(usuarioDTO);
        	
        	response.sendRedirect("ListarUsuariosServlet");
        	
		} catch (ParseException e) {
			System.err.println("Error al convertir a formato fecha " + e.getMessage());
			request.getRequestDispatcher("registro.jsp").forward(request, response);
		}
	}
}
