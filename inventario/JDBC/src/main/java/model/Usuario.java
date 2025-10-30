package model;

public class Usuario {
    private Long id;
    private String nombre;
    private String apellido;
    private String compra;
    


    // constructor 
    public Usuario (String nombre, String apellido, String compra ){
        this.nombre = nombre;
        this.apellido = apellido;
        this.compra = compra;

    }

    public Usuario(Long id, String nombre, String apellido, String compra ){
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.compra=compra;
    }


    public long getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }
    
    public String getApellido(){
        return apellido;
    }

    public String getCompra(){
        return compra;
    }

}