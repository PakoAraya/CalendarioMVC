package com.edutecno.dao;

import com.edutecno.dto.UsuarioDTO;
import com.edutecno.dto.HoroscopoDTO;
import com.edutecno.procesaconexion.AppConfig;
import com.edutecno.procesaconexion.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Método para obtener todos los usuarios
    public List<UsuarioDTO> traerTodosUsuarios() {
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();
        AppConfig.initialize();

        String query = "SELECT u.*, h.id AS horoscopo_id, h.animal, h.fecha_inicio, h.fecha_fin " +
                       "FROM usuarios u " +
                       "LEFT JOIN horoscopo h ON u.horoscopo_id = h.id";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                usuarioDTOList.add(mapearUsuarioDTO(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al traer los usuarios: " + e.getMessage());
        }
        return usuarioDTOList;
    }

    // Método para obtener un usuario por ID
    public UsuarioDTO traerUsuarioPorId(int idUsuario) {
        UsuarioDTO usuarioDTO = null;
        AppConfig.initialize();

        String query = "SELECT u.*, h.id AS horoscopo_id, h.animal, h.fecha_inicio, h.fecha_fin " +
                       "FROM usuarios u " +
                       "LEFT JOIN horoscopo h ON u.horoscopo_id = h.id " +
                       "WHERE u.id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                usuarioDTO = mapearUsuarioDTO(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al traer el usuario: " + e.getMessage());
        }
        return usuarioDTO;
    }

    // Método para agregar un nuevo usuario
    public void agregarUsuario(UsuarioDTO usuarioDTO) {
        AppConfig.initialize();

        String query = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password, horoscopo_id) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, usuarioDTO.getNombre());
            pstmt.setString(2, usuarioDTO.getUsername());
            pstmt.setString(3, usuarioDTO.getEmail());
            pstmt.setDate(4, new java.sql.Date(usuarioDTO.getFechaNacimiento().getTime()));
            pstmt.setString(5, usuarioDTO.getPassword());
            pstmt.setInt(6, usuarioDTO.getHoroscopoDTO().getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al agregar el usuario: " + e.getMessage());
        }
    }

    // Método para actualizar un usuario existente
    public void actualizarUsuario(UsuarioDTO usuarioDTO) {
        AppConfig.initialize();

        String query = "UPDATE usuarios SET nombre = ?, username = ?, email = ?, fecha_nacimiento = ?, " +
                       "password = ?, horoscopo_id = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, usuarioDTO.getNombre());
            pstmt.setString(2, usuarioDTO.getUsername());
            pstmt.setString(3, usuarioDTO.getEmail());
            pstmt.setDate(4, new java.sql.Date(usuarioDTO.getFechaNacimiento().getTime()));
            pstmt.setString(5, usuarioDTO.getPassword());
            pstmt.setInt(6, usuarioDTO.getHoroscopoDTO().getId());
            pstmt.setInt(7, usuarioDTO.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    // Método para eliminar un usuario por ID
    public void eliminarUsuario(int idUsuario) {
        AppConfig.initialize();

        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, idUsuario);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    // Método para mapear un ResultSet a un UsuarioDTO
    private UsuarioDTO mapearUsuarioDTO(ResultSet rs) throws SQLException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(rs.getInt("id"));
        usuarioDTO.setNombre(rs.getString("nombre"));
        usuarioDTO.setUsername(rs.getString("username"));
        usuarioDTO.setEmail(rs.getString("email"));
        usuarioDTO.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        usuarioDTO.setPassword(rs.getString("password"));

        // Mapear el HoroscopoDTO si está disponible
        if (rs.getInt("horoscopo_id") != 0) {
            HoroscopoDTO horoscopoDTO = new HoroscopoDTO();
            horoscopoDTO.setId(rs.getInt("horoscopo_id"));
            horoscopoDTO.setAnimal(rs.getString("animal"));
            horoscopoDTO.setFechaInicio(rs.getDate("fecha_inicio"));
            horoscopoDTO.setFechaFin(rs.getDate("fecha_fin"));
            usuarioDTO.setHoroscopoDTO(horoscopoDTO);
        }

        return usuarioDTO;
    }
}
