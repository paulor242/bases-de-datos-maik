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

        // eliminar 

        System.out.print("Ingrese id a eliminar: ");
        Long idE = scanner.nextLong();
        for(Usuario u : usuarioRepository.listaruUsuarios()) {
            if(u.getId().equals(id)) {
                usuarioRepository.listaruUsuarios(idE);
                System.out.println("Usuario eliminado");
                break;
            }
        }
    

        System.out.print("Ingrese id a actualizar: ");
        Long idA = scanner.nextLong();
        for(Usuario u : usuarioRepository.listaruUsuarios()) {
            if(u.getId().equals(id)) {
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.next();
                System.out.print("Nueva edad: ");
                int nuevaEdad = scanner.nextInt();
                u.setNombre(nuevoNombre);
                u.setEdad(nuevaEdad);
                usuarioRepository.listaruUsuarios(u);
                System.out.println("Usuario actualizado");
                break;
            }
        }

        scanner.close();
    }
}