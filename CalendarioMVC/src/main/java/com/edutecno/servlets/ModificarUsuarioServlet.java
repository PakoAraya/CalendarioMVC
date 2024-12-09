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


@WebServlet("/ModificarUsuarioServlet")
public class ModificarUsuarioServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Validacion de sesion y usuario administracion
		HttpSession session = request.getSession(false); // Obtener la sesión si existe
        if (session == null || session.getAttribute("username") == null) {
            // Si no está logueado, redirigir al Login
            response.sendRedirect("login.jsp");
            return;
        }
		
      //Obtener los parametros del formulario para modificar usuario
        int idUsuario = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String password = request.getParameter("password");
        int horoscopoId = Integer.parseInt(request.getParameter("horoscopoId"));
        
        try {
        	//Convertir la fecha de nacimiento
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
			
			//Crear UsuarioDTO
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(idUsuario);
			usuarioDTO.setNombre(nombre);
			usuarioDTO.setUsername(username);
			usuarioDTO.setEmail(email);
			usuarioDTO.setFechaNacimiento(fechaNacimiento);
			usuarioDTO.setPassword(password);
			usuarioDTO.setHoroscopoDTO(new HoroscopoDTO());
			usuarioDTO.getHoroscopoDTO().setId(horoscopoId);
			
			//Guardar en la base de datos
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.actualizarUsuario(usuarioDTO);
			
			response.sendRedirect("ListarUsuariosServlet");
        	
		} catch (ParseException e) {
			System.err.println("Error al procesar la fecha de nacimiento " + e.getMessage());
			request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
		}
	}

}
