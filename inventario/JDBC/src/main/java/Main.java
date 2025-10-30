import java.util.Scanner;
import repository.UsuarioRepository;
import repository.ProductosRepository;
import repository.AdminReposritory;
import model.Usuario;
import model.modelo.Productos;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioRepository usuarioRepository = new UsuarioRepository();
    private static ProductosRepository productosRepository = new ProductosRepository();
    private static AdminReposritory adminRepository = new AdminReposritory();

    public static void main(String[] args) {
        System.out.println("========MENU PRINCIPAL===============");
        System.out.println("Bienvenido al inventario de bicicletas:");
        System.out.println("Ingrese la opción que desea realizar:");
        System.out.println("1. Registrar un producto");
        System.out.println("2. Realizar una compra");
        System.out.println("3. Listar productos");
        System.out.println("4. Listar usuarios");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                registrarProducto();
                break;
            case 2:
                realizarCompra();
                break;
            case 3:
                listarProductos();
                break;
            case 4:
                listarUsuarios();
                break;
            default:
                System.out.println("La opción no es válida");
                break;
        }

        scanner.close();
    }

    private static void registrarProducto() {
        System.out.println("=== REGISTRAR PRODUCTO ===");
        System.out.println("Acceso restringido - Solo administradores");
        
        System.out.println("Ingrese su nombre de administrador:");
        String nombreAdmin = scanner.nextLine();
        
        System.out.println("Ingrese su contraseña:");
        String contraseña = scanner.nextLine();
        
        if (!adminRepository.validarAdmin(nombreAdmin, contraseña)) {
            System.out.println("Credenciales incorrectas. Acceso denegado.");
            return;
        }
        
        System.out.println("Acceso autorizado. Bienvenido " + nombreAdmin);
        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.nextLine();
        
        System.out.println("Ingrese el precio del producto:");
        double precio = scanner.nextDouble();
        
        System.out.println("Ingrese la cantidad en stock:");
        int stock = scanner.nextInt();
        scanner.nextLine();

        Productos producto = new Productos(nombre, precio, stock);
        productosRepository.InsertarProductosRepository(producto);
    }

    private static void realizarCompra() {
        System.out.println("=== REALIZAR COMPRA ===");
        
        try {
            // Verificar si hay productos disponibles
            if (productosRepository.listarProductos().isEmpty()) {
                System.out.println("No hay productos disponibles. Registre productos primero.");
                return;
            }
            
            // Mostrar productos disponibles
            System.out.println("Productos disponibles:");
            listarProductos();
            
            // Pedir datos del usuario
            System.out.println("Ingrese su nombre:");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese su apellido:");
            String apellido = scanner.nextLine();

            System.out.println("Ingrese el nombre del producto que va a comprar:");
            String compra = scanner.nextLine();

            // Verificar stock disponible
            int stockDisponible = productosRepository.obtenerStock(compra);
            if (stockDisponible <= 0) {
                System.out.println("Producto agotado o no encontrado en el inventario.");
                return;
            }

            // Buscar el precio del producto
            double precio = buscarPrecioProducto(compra);
            if (precio > 0) {
                System.out.println("************************************************************************************************************");
                System.out.println("El valor del producto seleccionado es: $" + precio);
                System.out.println("Stock disponible: " + stockDisponible + " unidades");
                System.out.println("************************************************************************************************************");
            } else {
                System.out.println("Producto no encontrado en el inventario.");
                return;
            }

            // Reducir stock y registrar compra
            if (productosRepository.reducirStock(compra)) {
                Usuario usuario = new Usuario(nombre, apellido, compra);
                usuarioRepository.insertarUsuario(usuario);
                System.out.println("Compra realizada exitosamente!");
                System.out.println("Stock restante: " + (stockDisponible - 1) + " unidades");
            } else {
                System.out.println("Error: No se pudo procesar la compra. Stock insuficiente.");
            }
        } catch (Exception e) {
            System.out.println("Error al realizar la compra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarProductos() {
        System.out.println("=== LISTA DE PRODUCTOS ===");
        try {
            var productos = productosRepository.listarProductos();
            if (productos.isEmpty()) {
                System.out.println("No hay productos registrados.");
                return;
            }
            
            for (Productos p : productos) {
                System.out.println("ID: " + p.getId_pro() + 
                    " | Nombre: " + p.getNombreP() + 
                    " | Precio: $" + p.getPrecio() + 
                    " | Stock: " + p.getStock());
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void listarUsuarios() {
        System.out.println("=== LISTA DE USUARIOS ===");
        try {
            var usuarios = usuarioRepository.listarUsuario();
            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.getId() +
                    " | Nombre: " + u.getNombre() +
                    " | Apellido: " + u.getApellido() +
                    " | Producto: " + u.getCompra());
            }
        } catch (Exception e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static double buscarPrecioProducto(String nombreProducto) {
        try {
            for (Productos p : productosRepository.listarProductos()) {
                if (p.getNombreP().equalsIgnoreCase(nombreProducto)) {
                    return p.getPrecio();
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }
        return 0;
    }
}
