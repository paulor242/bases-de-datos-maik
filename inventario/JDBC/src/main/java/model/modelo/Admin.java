package model.modelo;
import model.modelo.Admin;

public class Admin {
    private Long idAdmin;
    private String nombre;
    private String contraseña;

    /* Constructor para creacion de usuario */
    public Admin(String nombre, String contraseña ) {
        this.nombre = nombre;
        this.contraseña= contraseña;        
    }

    /* Constructor para listar usuarios */
    public Admin(Long idAdmin, String nombre, String contraseña) {
        
        this.idAdmin = idAdmin;
        this.nombre = nombre;
        this.contraseña= contraseña;
    }

    public Long getIdAAdmin() {
        return idAdmin;
    }

    public String getNombre() {
        return nombre;
    }
    public String getContraseña(){
        return contraseña;
    }

}
