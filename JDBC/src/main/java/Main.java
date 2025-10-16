import java.util.Scanner;

import model.Usuario;
import repository.UsuarioRepository;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioRepository usuarioRepository = new UsuarioRepository();

        /* Pedir nombre */
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        /* Pedir edad */
        System.out.print("Ingrese edad: ");
        Long edad = scanner.nextLong();

        Usuario usuario = new Usuario(nombre, edad);
        usuarioRepository.insertarUsuario(usuario);

        System.out.println("Lista de usuarios:");

        for(Usuario u: usuarioRepository.listaruUsuarios()) {
            System.out.println("Id: " + u.getId() + 
                                " Nombre: " + u.getNombre() +
                                " Edad: " + u.getEdad()
            );
        }

        // filtrar por id 
        System.out.print("Ingrese id a buscar: ");
        Long id = scanner.nextLong();
        for(Usuario u: usuarioRepository.listaruUsuarios()) {
            if(u.getId().equals(id)) {
                System.out.println("Id: " + u.getId() + 
                                " Nombre: " + u.getNombre() +
                                " Edad: " + u.getEdad()
                );
            }
        }

    

        scanner.close();
    }
}