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
    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (NOMBRE, EDAD) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setLong(2, usuario.getEdad());

            preparedStatement.executeUpdate();

            System.out.println("Usuario insertado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listaruUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";

        try (Connection connection = Conexion.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                usuarios.add(new Usuario(
                        resultSet.getLong("id"),
                        resultSet.getString("nombre"),
                        resultSet.getLong("edad")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        public void eliminar(Long id) {
        usuarios.removeIf(u -> u.getId().equals(id));
        }

        public void actualizar( String nombre, Long edad) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                u.setNombre(nombre);
                u.setEdad(edad);
                break;
            }
        }
    }

        return usuarios;
    }
}
