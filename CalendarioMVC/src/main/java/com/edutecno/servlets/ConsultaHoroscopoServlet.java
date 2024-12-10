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

		// Validación de sesión y usuario administrador antes de continuar
		HttpSession session = request.getSession(false); // Obtener la sesión si existe
		if (session == null || session.getAttribute("username") == null) {
			// Si no está logueado, redirigir al Login
			response.sendRedirect("login.jsp");
			return;
		}

		// Obtener el parámetro fecha de nacimiento del formulario
		String fechaNacimientoStr = request.getParameter("fechaNacimiento");
		if (fechaNacimientoStr == null || fechaNacimientoStr.isEmpty()) {
			// Si no se proporciona una fecha válida, mostrar error
			request.setAttribute("error", "Debe ingresar una fecha de nacimiento válida.");
			request.getRequestDispatcher("consultaHoroscopo.jsp").forward(request, response);
			return;
		}

		try {
			// Definir múltiples formatos de fecha para aceptar tanto "yyyy-MM-dd" como "dd-MM-yyyy"
			SimpleDateFormat[] dateFormats = new SimpleDateFormat[] {
					new SimpleDateFormat("yyyy-MM-dd"),
					new SimpleDateFormat("dd-MM-yyyy"),
					new SimpleDateFormat("dd-MM-aaaa")
			};

			Date fechaNacimiento = null;
			for (SimpleDateFormat format : dateFormats) {
				try {
					fechaNacimiento = format.parse(fechaNacimientoStr);  // Intentar convertir la fecha
					break;  // Salir del bucle si la conversión tiene éxito
				} catch (ParseException e) {
					// Continuar con el siguiente formato si el actual no es válido
				}
			}

			// Si no se pudo parsear la fecha con ninguno de los formatos, mostrar error
			if (fechaNacimiento == null) {
				request.setAttribute("error", "Formato de fecha inválido. Use dd-MM-yyyy o yyyy-MM-dd.");
				request.getRequestDispatcher("consultaHoroscopo.jsp").forward(request, response);
				return;
			}

			// Obtener la lista de horóscopos desde el DAO
			HoroscopoDAO horoscopoDAO = new HoroscopoDAO();
			List<HoroscopoDTO> listaHoroscopo = horoscopoDAO.obtenerHoroscopo();

			// Determinar el horóscopo correspondiente basado en la fecha
			HoroscopoDTO horoscopo = null;
			for (HoroscopoDTO temp : listaHoroscopo) {
				if (fechaNacimiento.after(temp.getFechaInicio()) && fechaNacimiento.before(temp.getFechaFin())) {
					horoscopo = temp;
					break;  // Encontró el horóscopo correspondiente
				} else if (fechaNacimiento.equals(temp.getFechaInicio()) || fechaNacimiento.equals(temp.getFechaFin())) {
					horoscopo = temp;
					break;  // Encontró el horóscopo correspondiente
				}
			}

			// Verificar si se encontró un horóscopo
			if (horoscopo != null) {
				request.setAttribute("horoscopo", horoscopo);  // Enviar el horóscopo a la vista
				request.getRequestDispatcher("resultadoHoroscopo.jsp").forward(request, response);
			} else {
				// Si no se encontró un horóscopo para la fecha proporcionada
				request.setAttribute("error", "No se encontró un horóscopo para la fecha ingresada.");
				request.getRequestDispatcher("consultaHoroscopo.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// Manejo general de excepciones
			request.setAttribute("error", "Error al procesar la fecha.");
			request.getRequestDispatcher("consultaHoroscopo.jsp").forward(request, response);
		}
	}
}
