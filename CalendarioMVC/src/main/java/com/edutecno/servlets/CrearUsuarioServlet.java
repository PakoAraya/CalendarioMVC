package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

            // Redirigir al login después del registro exitoso
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al procesar el registro. Inténtalo de nuevo.");
            request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
        }
    }
}
