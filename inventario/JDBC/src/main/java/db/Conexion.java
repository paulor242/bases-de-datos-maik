package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // ruta de conexion
    private static final String URL="jdbc:mysql://localhost:3306/inventario";
    private static final String USER="root";
    private static final String PASSWORD ="";

    // retorna la conexion lista para usar
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}