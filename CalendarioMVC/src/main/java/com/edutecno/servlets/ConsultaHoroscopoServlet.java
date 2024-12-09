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
import java.util.List;

import com.edutecno.dao.HoroscopoDAO;
import com.edutecno.dto.HoroscopoDTO;

@WebServlet("/ConsultaHoroscopoServlet")
public class ConsultaHoroscopoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirigir al formulario de consulta
        response.sendRedirect("consultaHoroscopo.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Validación de sesión y usuario administrador
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("username"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener el parámetro fecha de nacimiento del formulario
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        if (fechaNacimientoStr == null || fechaNacimientoStr.isEmpty()) {
            request.setAttribute("error", "Debe ingresar una fecha de nacimiento válida.");
            request.getRequestDispatcher("consultaHoroscopo.jsp").forward(request, response);
            return;
        }

        try {
            // Convertir la fecha de nacimiento a un objeto Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = sdf.parse(fechaNacimientoStr);

            // Obtener la lista de horóscopos desde el DAO
            HoroscopoDAO horoscopoDAO = new HoroscopoDAO();
            List<HoroscopoDTO> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();

            // Determinar el horóscopo correspondiente
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

            // Verificar si se encontró un horóscopo
            if (horoscopo != null) {
                request.setAttribute("horoscopo", horoscopo);
                request.getRequestDispatcher("resultadosHoroscopo.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No se encontró un horóscopo para la fecha ingresada.");
                request.getRequestDispatcher("consultaHoroscopo.jsp").forward(request, response);
            }

        } catch (ParseException e) {
            // Manejo de error en la conversión de fecha
            request.setAttribute("error", "Formato de fecha inválido. Use el formato yyyy-MM-dd.");
            request.getRequestDispatcher("consultaHoroscopo.jsp").forward(request, response);
        }
    }
}
