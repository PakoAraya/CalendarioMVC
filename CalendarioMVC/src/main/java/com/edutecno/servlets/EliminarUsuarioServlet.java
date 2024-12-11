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
 * Servlet implementation class EliminarUsuarioServlet
 */
@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Obtener el ID del usuario a eliminar
        String idUsuarioStr = request.getParameter("id");
        if (idUsuarioStr == null || idUsuarioStr.isEmpty()) {
            request.setAttribute("error", "No se proporcionó un ID de usuario válido.");
            // Recargar la lista de usuarios para evitar que el JSP quede vacío
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<UsuarioDTO> usuarios = usuarioDAO.traerTodosUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("eliminarUsuario.jsp").forward(request, response);
            return;
        }

        try {
            int idUsuario = Integer.parseInt(idUsuarioStr);

            // Eliminar el usuario
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.eliminarUsuario(idUsuario);

            // Cargar la lista de usuarios actualizada
            List<UsuarioDTO> usuarios = usuarioDAO.traerTodosUsuarios();
            request.setAttribute("usuarios", usuarios);

            // Redirigir al JSP de eliminación
            request.getRequestDispatcher("eliminarUsuario.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID de usuario no válido.");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            List<UsuarioDTO> usuarios = usuarioDAO.traerTodosUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("eliminarUsuario.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
