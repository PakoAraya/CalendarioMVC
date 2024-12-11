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
        // Validación de sesión y usuario administración
        HttpSession session = request.getSession(false); // Obtener la sesión si existe
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener los parámetros del formulario para modificar usuario
        try {
            int idUsuario = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String fechaNacimientoStr = request.getParameter("fechaNacimiento");
            String password = request.getParameter("password");
            int horoscopoId = Integer.parseInt(request.getParameter("horoscopoId"));

            // Convertir la fecha de nacimiento
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);

            // Crear UsuarioDTO
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(idUsuario);
            usuarioDTO.setNombre(nombre);
            usuarioDTO.setUsername(username);
            usuarioDTO.setEmail(email);
            usuarioDTO.setFechaNacimiento(fechaNacimiento);
            usuarioDTO.setPassword(password);
            usuarioDTO.setHoroscopoDTO(new HoroscopoDTO());
            usuarioDTO.getHoroscopoDTO().setId(horoscopoId);

            // Guardar en la base de datos
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.actualizarUsuario(usuarioDTO);

            // Redirigir a la página de listado de usuarios
            response.sendRedirect("ListarUsuariosServlet");

        } catch (ParseException e) {
            // Si la fecha tiene formato incorrecto, mostrar error
            System.err.println("Error al procesar la fecha de nacimiento: " + e.getMessage());
            request.setAttribute("error", "Formato de fecha incorrecto. Por favor, use yyyy-MM-dd.");
            request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            // Si los números tienen formato incorrecto (por ejemplo, id o horoscopoId), mostrar error
            System.err.println("Error al procesar datos numéricos: " + e.getMessage());
            request.setAttribute("error", "Datos numéricos inválidos.");
            request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
        } catch (Exception e) {
            // Manejo de error general
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            request.setAttribute("error", "Hubo un error al intentar modificar el usuario.");
            request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Validación de sesión y usuario administración
        HttpSession session = request.getSession(false); // Obtener la sesión si existe
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            request.setAttribute("error", "ID de usuario no proporcionado.");
            request.getRequestDispatcher("/listarUsuarios.jsp").forward(request, response);
            return;
        }

        try {
            int idUsuario = Integer.parseInt(idStr);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioDTO usuario = usuarioDAO.traerUsuarioPorId(idUsuario);

            if (usuario != null) {
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/modificarUsuario.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Usuario no encontrado.");
                request.getRequestDispatcher("/listarUsuarios.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            // Error en el formato del ID
            request.setAttribute("error", "El ID proporcionado no es válido.");
            request.getRequestDispatcher("/listarUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
            // Error general
            request.setAttribute("error", "No se pudo encontrar el usuario.");
            request.getRequestDispatcher("/listarUsuarios.jsp").forward(request, response);
        }
    }
}
