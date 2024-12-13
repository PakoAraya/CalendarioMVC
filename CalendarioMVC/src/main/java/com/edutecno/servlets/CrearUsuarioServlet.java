package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.edutecno.dao.UsuarioDAO;
import com.edutecno.dao.HoroscopoDAO;
import com.edutecno.dto.UsuarioDTO;
import com.edutecno.dto.HoroscopoDTO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/CrearUsuarioServlet")
public class CrearUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Verificar si ya hay una sesión activa con un usuario
            HttpSession session = request.getSession(false);
            UsuarioDTO usuarioActivo = (session != null) ? (UsuarioDTO) session.getAttribute("usuario") : null;

            // Obtener parámetros del formulario
            String nombre = request.getParameter("nombre");
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fechaNacimientoStr = request.getParameter("fecha_nacimiento");

            // Validar los datos obligatorios
            if (nombre == null || username == null || email == null || password == null || fechaNacimientoStr == null) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
                return;
            }

            // Convertir la fecha de nacimiento
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);

            // Determinar el horóscopo correspondiente
            HoroscopoDAO horoscopoDAO = new HoroscopoDAO();
            List<HoroscopoDTO> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();
            HoroscopoDTO horoscopo = null;

            for (HoroscopoDTO temp : listaHoroscopo) {
                if (fechaNacimiento.after(temp.getFechaInicio()) && fechaNacimiento.before(temp.getFechaFin())) {
                    horoscopo = temp;
                    break;
                } else if (fechaNacimiento.equals(temp.getFechaInicio()) || fechaNacimiento.equals(temp.getFechaFin())) {
                    horoscopo = temp;
                    break;
                }
            }

            if (horoscopo == null) {
                request.setAttribute("error", "No se encontró un horóscopo para la fecha ingresada.");
                request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
                return;
            }

            // Crear el objeto UsuarioDTO
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setNombre(nombre);
            usuarioDTO.setUsername(username);
            usuarioDTO.setEmail(email);
            usuarioDTO.setPassword(password);
            usuarioDTO.setFechaNacimiento(fechaNacimiento);
            usuarioDTO.setHoroscopoDTO(horoscopo); // Asociar el horóscopo encontrado

            // Registrar el usuario en la base de datos
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.agregarUsuario(usuarioDTO);

            // Si no hay sesión activa (es decir, el usuario no está logueado)
            if (usuarioActivo == null) {
                // Redirigir a login.jsp después de registrarse desde login
                response.sendRedirect("login.jsp");
                return;
            }

            // Obtener el parámetro "redirigir" de la solicitud
            String redirigir = request.getParameter("redirigir");

            // Si el parámetro "redirigir" es "listar", se redirige a listarUsuario.jsp solo si hay sesión activa
            if ("listar".equals(redirigir)) {
                if (usuarioActivo != null) {
                    response.sendRedirect("listarUsuario.jsp");
                } else {
                    response.sendRedirect("login.jsp"); // Si no está logueado, redirigir a login
                }
            } else {
                // Si no se especifica redirigir, siempre llevar a login
                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al procesar el registro. Inténtalo de nuevo.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
        }
    }
}
