package com.edutecno.procesaconexion;

//Clase configurada con mejores practicas de Try Catch en caso de error en conexion a base de datos
public class AppConfig {
  public static void initialize() {
      try {
          DatabaseUtil.configure(
              "jdbc:postgresql://pg-27b405f-franarayah-postgresql.b.aivencloud.com:13195/defaultdb?ssl=require",
              "avnadmin",
              "AVNS_zZq2-USoxo5N9Lv32j_"
          );
          System.out.println("Base de datos configurada correctamente");
      } catch (Exception e) {
          System.err.println("Error al configurar la base de datos: " + e.getMessage());
          e.printStackTrace();
      }
  }
}
