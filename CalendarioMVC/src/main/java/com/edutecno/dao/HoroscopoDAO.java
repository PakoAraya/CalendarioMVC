package com.edutecno.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edutecno.dto.HoroscopoDTO;
import com.edutecno.modelo.Horoscopo;
import com.edutecno.procesaconexion.AppConfig;
import com.edutecno.procesaconexion.DatabaseUtil;

public class HoroscopoDAO {
	//Metodo para obtener todos los regisgtros de la tabla Horoscopo de la base de datos
	public List<HoroscopoDTO> obtenerHoroscopo(){
		List<HoroscopoDTO> horoscopoDTOList = new ArrayList<>();
		AppConfig.initialize();
		
		String query = "SELECT * FROM horoscopo;";
		try (Connection connection = DatabaseUtil.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query);
			 ResultSet rs = pstmt.executeQuery()){
			
			while(rs.next()) {
				Horoscopo horoscopo = new Horoscopo();
				horoscopo.setId(rs.getInt("id"));
				horoscopo.setAnimal(rs.getString("animal"));
				horoscopo.setFechaInicio(rs.getDate("fecha_inicio"));
				horoscopo.setFechaFin(rs.getDate("fecha_fin"));
				
				//Llenamos los datos en HoroscopoDTO
				horoscopoDTOList.add(new HoroscopoDTO(horoscopo));
			}
		} catch (SQLException e) {
			System.err.println("Error al traer datos del horoscopo " + e.getMessage());
		}
		return horoscopoDTOList;
	}
	
	//Metodo para obtener un horoscopo por ID
	public HoroscopoDTO traerHoroscopoPorId(int idHoroscopo) {
		HoroscopoDTO horoscopoDTO = null;
		AppConfig.initialize();
		
		String query = "SELECT * FROM horoscopo WHERE id = ?;";
		try (Connection connection = DatabaseUtil.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query)){
			
			pstmt.setInt(1, idHoroscopo);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Horoscopo horoscopo = new Horoscopo();
				horoscopo.setId(rs.getInt("id"));
				horoscopo.setAnimal(rs.getString("animal"));
				horoscopo.setFechaInicio(rs.getDate("fecha_inicio"));
				horoscopo.setFechaFin(rs.getDate("fecha_fin"));
				
				//Llenamos el DTO con los datos obtenidos
				horoscopoDTO = new HoroscopoDTO(horoscopo);
			}
		} catch (SQLException e) {
			System.err.println("Error al traer el horoscopo " + e.getMessage());
		}
		return horoscopoDTO;
	}
	
	//Metodo para agregar un nuevo horoscopo
	public void agregarHoroscopo(HoroscopoDTO horoscopoDTO) {
		AppConfig.initialize();
		
		String query = "INSERT INTO horoscopo (animal, fecha_inicio, fecha_fin) VALUES (?, ?, ?)";
		try (Connection connection = DatabaseUtil.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query)){
			
			pstmt.setString(1, horoscopoDTO.getAnimal());
			pstmt.setDate(2, new java.sql.Date(horoscopoDTO.getFechaInicio().getTime()));
			pstmt.setDate(3, new java.sql.Date(horoscopoDTO.getFechaFin().getTime()));
			
			//Ejecutamos la consulta
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Error al agregar el horoscopo " + e.getMessage());
		}
	}
	
	//Metodo para eliminar un horoscopo
	public void eliminarHoroscopo(int idHoroscopo) {
		AppConfig.initialize();
		
		String query = "DELETE FROM horoscopo WHERE id = ?;";
		try (Connection connection = DatabaseUtil.getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query)){
			
			pstmt.setInt(1, idHoroscopo);
			
			//Ejecutamos la eliminacion del registro
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("Error al eliminar el horoscopo " + e.getMessage());
		}
	}
	
	// Método para actualizar un horóscopo existente
    public void actualizarHoroscopo(HoroscopoDTO horoscopoDTO) {
        AppConfig.initialize(); // Inicializamos la configuración de la base de datos

        String query = "UPDATE horoscopo SET animal = ?, fecha_inicio = ?, fecha_fin = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, horoscopoDTO.getAnimal());
            pstmt.setDate(2, new java.sql.Date(horoscopoDTO.getFechaInicio().getTime()));
            pstmt.setDate(3, new java.sql.Date(horoscopoDTO.getFechaFin().getTime()));
            pstmt.setInt(4, horoscopoDTO.getId());

            // Ejecutamos la actualización
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar el horóscopo: " + e.getMessage());
        }
    }
}
