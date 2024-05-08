
package proyecto4;

import java.text.ParseException;
import java.util.Scanner;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        CRUD library = Seeder.initialize();
        ConsoleReader consoleReader = new ConsoleReader();

        while (true) {
            System.out.println("\nPor favor, selecciona una opción:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    String username = consoleReader.readString("Nombre de usuario: ", Validators.usernameValidator);
                    String password = consoleReader.readPassword("Contraseña: ", Validators.passwordValidator);

                    User user = library.logind(username, password);

                    if (user == null) {
                        System.out.println("Nombre de usuario o contraseña incorrectos.");
                    } else if (user instanceof Administrator) {
                        Administrator adminUser = (Administrator) user;
                        


if (adminUser.isIsSuperAdmin()) {
                            Menu menu = new Menu(library);
                            while (!menu.isExit()) {
                                 System.out.println();
                          System.out.println("¡Bienvenido superAdmin!");      
       menu.addOption("1",menu::Listadodetodosloslibros );
       menu.addOption("2", menu::Actualizacióndelibro);
       menu.addOption("3", menu::Listadodetodoslosautores);
       menu.addOption("4", menu::Añadirautor);
       menu.addOption("5", menu::Añadirnuevolibro);
       menu.addOption("6", menu::Añadirnuevocliente);
       menu.addOption("7", menu::ActualizarAutor);
       menu.addOption("8", menu::Eliminarcliente);
       menu.addOption("9", menu::Mostrarlibrosprestados);  
       menu.addOption("10", menu::Eliminarlibro);
       menu.addOption("11", menu::Prestamosydevolucionesdelibros);
       menu.addOption("12", menu::Generarreportedemovimientos);
       menu.addOption("13", menu::Eliminarautor);
       menu.addOption("14", menu::CRUDparaSuperadmin); 
       menu.addOption("15", menu::Salir); 
        
           menu.Menu1();

            String adminOption = scanner.nextLine();
                                menu.execute(adminOption);

}
                        } else {
                            Menu menu2 = new Menu(library);
                            while (!menu2.isExit()) {
                                System.out.println();
                                System.out.println("¡BienvenidoAdmin!");
       menu2.addOption("1",menu2::Listadodetodosloslibros );
       menu2.addOption("2", menu2::Actualizacióndelibro);
       menu2.addOption("3", menu2::Listadodetodoslosautores);
       menu2.addOption("4", menu2::Añadirautor);
       menu2.addOption("5", menu2::Añadirnuevolibro);
       menu2.addOption("6", menu2::Añadirnuevocliente);
       menu2.addOption("7", menu2::ActualizarAutor);
       menu2.addOption("8", menu2::Eliminarcliente);
       menu2.addOption("9", menu2::Mostrarlibrosprestados);  
       menu2.addOption("10", menu2::Eliminarlibro);
       menu2.addOption("11", menu2::Prestamosydevolucionesdelibros);
       menu2.addOption("12", menu2::Generarreportedemovimientos);
       menu2.addOption("13", menu2::Eliminarautor);
       menu2.addOption("14", menu2::Salir); 
        
            menu2.Menu2();

            String normalAdminOption = scanner.nextLine();
                                menu2.execute(normalAdminOption);
            
            
           }}} else {
                        Menu menu3 = new Menu(library);
                         System.out.println();
                        System.out.println("¡Bienvenido, cliente!");

                        while (!menu3.isExit()) {
       menu3.addOption("1",menu3::Listadodetodosloslibros );    
       menu3.addOption("2",menu3::Vertranssacciones );    
       menu3.addOption("3", menu3::Salir); 
    
            menu3.Menu3();

     String clienteOption = scanner.nextLine();
                            menu3.execute(clienteOption);
                        }
                        System.out.println("¡Hasta luego!");
                    }
                    break;
                case "2":
                    System.out.println("¡Nos vemos!");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
                    break;
            }
        }
    }  
}

