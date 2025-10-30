package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Conexion;
import model.Usuario;


public class UsuarioRepository {

    public void insertarUsuario(Usuario usuario){
        String sql ="INSERT INTO usuario(nombre, apellido, compra) values(?, ?, ?)";

        try (Connection connection =Conexion.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, usuario.getNombre());
                preparedStatement.setString(2, usuario.getApellido());
                preparedStatement.setString(3, usuario.getCompra());

                preparedStatement.executeUpdate();
                System.out.println("usuario registrado exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Usuario> listarUsuario(){
        List<Usuario> usuario = new ArrayList<>();
        String sql =" SELECT * FROM usuario";
        try (Connection connection =Conexion.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) { 
                usuario.add(new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"), 
                        resultSet.getString("apellido"), 
                        resultSet.getString("compra")
                    )
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;

    }
}
