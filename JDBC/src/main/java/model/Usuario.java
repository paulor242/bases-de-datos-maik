package model;

public class Usuario {
    private Long id;
    private String nombre;
    private Long edad;

    /* Constructor para creacion de usuario */
    public Usuario(String nombre, Long edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    /* Constructor para listar usuarios */
    public Usuario(Long id, String nombre, Long edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getEdad() {
        return edad;
    }
}
