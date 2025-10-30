package repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.modelo.Productos;

public class ProductosRepository {
    public void InsertarProductosRepository (Productos productos){
        String sql ="INSERT INTO productos (nombre, precio, stock) values (?, ?, ?)";
        
        try (Connection connection =Conexion.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(sql)){
            preparedStatement.setString(1, productos.getNombreP());
            preparedStatement.setDouble(2, productos.getPrecio());
            preparedStatement.setInt(3, productos.getStock());

            preparedStatement.executeLargeUpdate();
            System.out.println("Producto registrado exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Productos> listarProductos(){
        List<Productos> productos= new ArrayList<>();
        String sql ="SELECT * FROM productos";
        
        try(Connection connection= Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            
            while (resultSet.next()){
                    productos.add(new Productos(
                    resultSet.getLong("id"),
                    resultSet.getString("nombre"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("stock")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }
    
    public boolean reducirStock(String nombreProducto) {
        String sql = "UPDATE productos SET stock = stock - 1 WHERE nombre = ? AND stock > 0";
        
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreProducto);
            
            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int obtenerStock(String nombreProducto) {
        String sql = "SELECT stock FROM productos WHERE nombre = ?";
        
        try (Connection connection = Conexion.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreProducto);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("stock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
