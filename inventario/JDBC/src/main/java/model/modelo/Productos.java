package model.modelo;

public  class Productos {
    private Long id_pro;
    private String  nombreP;
    private Double precio;
    private Integer stock;


    // constructor para la creacion de los productos 
    public Productos(String nombreP, Double precio, Integer stock){
        this.nombreP = nombreP;
        this.precio = precio;
        this.stock = stock;
    }

    // Constructor para listar los productos
    public Productos (Long id_pro, String nombreP, Double precio, Integer stock){
        this.id_pro = id_pro;
        this.nombreP = nombreP;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId_pro(){
        return id_pro;
    }

    public String getNombreP(){
        return nombreP;
    }

    public Double getPrecio(){
        return precio;
    }

    public Integer getStock(){
        return stock;
    }

}
