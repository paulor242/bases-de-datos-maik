package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.Conexion;
import model.modelo.Admin;

public class AdminReposritory {

    public void insertarAdmin(Admin admin){
        String sql = "INSERT INTO admin(nombre, contraseña) VALUES(?, ?)";
        
        try (Connection connection = Conexion.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getNombre());
            preparedStatement.setString(2, admin.getContraseña());
            
            preparedStatement.executeUpdate();
            System.out.println("Administrador registrado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean validarAdmin(String nombre, String contraseña) {
        String sql = "SELECT * FROM admin WHERE nombre = ? AND contraseña = ?";
        
        try (Connection connection = Conexion.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, contraseña);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
