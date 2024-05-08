
package proyecto4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu extends Principal implements Controller{

 
   
    private Map<String, Runnable> options = new HashMap<>();
    private CRUD library;
    private boolean exit = false;
    public Menu(CRUD library) {
        this.library = library;
    }

    
    public void addOption(String key, Runnable action) {
        options.put(key, action);
    }

    @Override 
    public void execute(String key) {
        Runnable action = options.get(key);
        if (action != null) {
            action.run();
        } else {
            System.out.println("Opción no válida.");
        }
    }
    Scanner scanner=new Scanner(System.in);
    
    

    
    
    public void Listadodetodosloslibros() {    
     System.out.println("");                
    System.out.println("Listado de todos los libros:");
    ArrayList<Book> books = library.getBooks();
    if (books.isEmpty()) {
        System.out.println("No hay libros disponibles en la biblioteca.\n");
        return;
    }
    for (Book book : books) {
        if (book.getIsAvailable()) {
            System.out.println("ISBN: " + book.getIsbn() + ", Título: " + book.getTitle());
        }
    }
    System.out.println();

    }
    
    
    
    

    public void Actualizacióndelibro() {
       System.out.println(""); 
    System.out.println("Actualización de libro:");
    System.out.println("Libros disponibles:");
    ArrayList<Book> availableBooks = library.getBooks();
    if (availableBooks.isEmpty()) {
        System.out.println("No hay libros disponibles en la biblioteca.\n");
        return;
    }
    for (int i = 0; i < availableBooks.size(); i++) {
        Book book = availableBooks.get(i);
        System.out.println((i+1) + ". ISBN: " + book.getIsbn() + ", Título: " + book.getTitle());
    }

   
    System.out.print("Seleccione el libro que desea actualizar (ingrese el número correspondiente): ");
    int bookIndex = Integer.parseInt(scanner.nextLine()) - 1;
    if (bookIndex < 0 || bookIndex >= availableBooks.size()) {
        System.out.println("Opción inválida.\n");
        return;
    }
    Book selectedBookToUpdate = availableBooks.get(bookIndex);

   
    System.out.print("Ingrese el nuevo título del libro: ");
    String newTitle = scanner.nextLine();
    selectedBookToUpdate.setTitle(newTitle);

  
    System.out.println("Autores disponibles:");
    ArrayList<Author> availableAuthors = library.getAuthors();
    if (availableAuthors.isEmpty()) {
        System.out.println("No hay autores disponibles en la biblioteca. Debe agregar al menos un autor primero.\n");
        return;
    }
    for (int i = 0; i < availableAuthors.size(); i++) {
        Author author = availableAuthors.get(i);
        System.out.println((i+1) + ". " + author.getProfile().getName() + " " + author.getProfile().getLastName());
    }

   
    System.out.print("Seleccione el autor del libro (ingrese el número correspondiente): ");
    int authorIndex = Integer.parseInt(scanner.nextLine()) - 1;
    if (authorIndex < 0 || authorIndex >= availableAuthors.size()) {
        System.out.println("Opción inválida.\n");
        return;
    }
    Author selectedAuthor = availableAuthors.get(authorIndex);
    selectedBookToUpdate.setAuthor(selectedAuthor);

   
    library.updateBook(selectedBookToUpdate);
    System.out.println("Libro actualizado correctamente.\n");  
    }
    
    
    
    
    

    public void Listadodetodoslosautores() {
        System.out.println(""); 
    ArrayList<Author> authorsList = library.getAuthors();
    if (authorsList.isEmpty()) {
        System.out.println("No hay autores en la biblioteca.\n");
    } else {
        System.out.println("Lista de autores:");
        for (Author authors : authorsList) {
            System.out.println("Nombre: " + authors.getProfile().getName() + " " + authors.getProfile().getLastName());
        }
        System.out.println();
    }}


    
    
    
    
    public void Añadirautor() {
      System.out.println("");                
    System.out.println("Añadir nuevo autor:");
    System.out.print("Ingrese el nombre del autor: ");
    String authorName = scanner.nextLine();
    System.out.print("Ingrese el apellido del autor: ");
    String authorLastName = scanner.nextLine();
    System.out.print("Ingrese la fecha de nacimiento del autor (en formato dd/mm/yyyy): ");
    String birthdateStr = scanner.nextLine();
    Date birthdate = null;
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        birthdate = dateFormat.parse(birthdateStr);
    } catch (ParseException e) {
        System.out.println("Error: Formato de fecha incorrecto. Use el formato dd/mm/yyyy.");
        return;
    }
    Profile newAuthorProfile = new Profile(authorName, authorLastName, birthdate);
    Author newAuthor = new Author(newAuthorProfile, new ArrayList<>());
    library.createAuthor(newAuthor);
    System.out.println("Autor agregado correctamente.\n");

    }
    
    
    
    

    public void Añadirnuevolibro() {
      System.out.println("");   
System.out.println("Añadir nuevo libro:");
System.out.print("Ingrese el ISBN del libro: ");
String isbn = scanner.nextLine();
System.out.print("Ingrese el título del libro: ");
String title = scanner.nextLine();

ArrayList<Author> authorsList = library.getAuthors();
if (authorsList.isEmpty()) {
    System.out.println("No hay autores en la biblioteca.\n");
    return; 
} else {
    System.out.println("Lista de autores:");
    for (int i = 0; i < authorsList.size(); i++) {
        System.out.println((i+1) + ". " + authorsList.get(i).getProfile().getName() + " " + authorsList.get(i).getProfile().getLastName());
    }
    System.out.println();
}

int authorIndex = -1;
while (authorIndex < 0 || authorIndex >= authorsList.size()) {
    try {
        System.out.print("Ingrese el número correspondiente al autor del libro: ");
        authorIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (authorIndex < 0 || authorIndex >= authorsList.size()) {
            System.out.println("Error: El número de autor especificado no es válido.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Error: Ingrese un número válido.");
    }
}

Author selectedAuthor = authorsList.get(authorIndex);

System.out.print("Ingrese la fecha de publicación del libro (en formato dd/mm/yyyy): ");
String publishDateStr = scanner.nextLine();
Date publishDate;
try {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    publishDate = dateFormat.parse(publishDateStr);
} catch (ParseException e) {
    System.out.println("Error: Formato de fecha incorrecto. Use el formato dd/mm/yyyy.\n");
    return; 
}

Book newBook = new Book(isbn, title, selectedAuthor, publishDate, true);
library.createBook(newBook);
System.out.println("Libro agregado correctamente.\n");


    }
    
    
    
    

    public void Añadirnuevocliente() {
        System.out.println("Ingrese los datos del cliente:");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Apellido: ");
        String lastName = scanner.nextLine();
        System.out.print("Nombre de usuario: ");
        String usernamE = scanner.nextLine();
        System.out.print("Contraseña: ");
        String passworD = scanner.nextLine();
        System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
        String birthDateString = scanner.nextLine();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {   
            Date birthDate = dateFormat.parse(birthDateString);
            Profile clientProfile = new Profile(name, lastName, birthDate);
            Client client = new Client(clientProfile, usernamE, passworD, new ArrayList<>());
            library.createClients(client);
        } catch (ParseException e) {
            System.out.println("Error al analizar la fecha. Formato incorrecto.");
        }

    }
    
    
    

    public void ActualizarAutor() {
        System.out.println("");                
     System.out.println("Actualización de autor:");

    
    System.out.println("Autores disponibles:");
    ArrayList<Author> availableAuthorss = library.getAuthors();
    if (availableAuthorss.isEmpty()) {
        System.out.println("No hay autores disponibles en la biblioteca. Debe agregar al menos un autor primero.\n");
        return;
    }
    for (int i = 0; i < availableAuthorss.size(); i++) {
        Author author = availableAuthorss.get(i);
        System.out.println((i+1) + ". " + author.getProfile().getName() + " " + author.getProfile().getLastName());
    }

   
    System.out.print("Seleccione el autor que desea actualizar (ingrese el número correspondiente): ");
    int auythorIndex = Integer.parseInt(scanner.nextLine()) - 1;
    if (auythorIndex < 0 || auythorIndex >= availableAuthorss.size()) {
        System.out.println("Opción inválida.\n");
        return;
    }
    Author selectedAuthorToUpdate = availableAuthorss.get(auythorIndex);

   
    System.out.print("Ingrese el nuevo nombre del autor: ");
    String newName = scanner.nextLine();
    selectedAuthorToUpdate.getProfile().setName(newName);

    
    System.out.print("Ingrese el nuevo apellido del autor: ");
    String newLastName = scanner.nextLine();
    selectedAuthorToUpdate.getProfile().setLastName(newLastName);

    
    library.updateAuthor(selectedAuthorToUpdate);
    System.out.println("Autor actualizado correctamente.\n");
    }
    
    
    
    

    public void Eliminarcliente() {
        System.out.println("");              
                    
    System.out.println("Eliminar cliente:");
    
    
    System.out.println("Clientes disponibles:");
    ArrayList<Client> availableClints = library.getClients();
    if (availableClints.isEmpty()) {
        System.out.println("No hay clientes disponibles en la biblioteca para eliminar.\n");
        return;
    }
    for (int i = 0; i < availableClints.size(); i++) {
        Client client = availableClints.get(i);
        System.out.println((i+1) + ". " + client.getProfile().getName() + " " + client.getProfile().getLastName());
    }
    
    
    System.out.print("Seleccione el cliente que desea eliminar (ingrese el número correspondiente): ");
    int clientInde = Integer.parseInt(scanner.nextLine()) - 1;
    if (clientInde < 0 || clientInde >= availableClints.size()) {
        System.out.println("Opción inválida.\n");
        return;
    }
    Client clientToDelete = availableClints.get(clientInde);
    

    if (!clientToDelete.getBorrowedBooks().isEmpty()) {
        System.out.println("El cliente tiene libros prestados y no se puede eliminar.\n");
        return;
    }
    
    
    library.deleteClient(clientToDelete.getProfile().getName());
    System.out.println("Cliente eliminado correctamente.\n");


    }
    
    
    

    public void Mostrarlibrosprestados() {
      System.out.println(""); 
     System.out.println("Lista de libros prestados:");
    ArrayList<Client> clients = library.getClients();
    for (Client client : clients) {
        ArrayList<Book> borrowedBooks = client.getBorrowedBooks();
        if (!borrowedBooks.isEmpty()) {
            System.out.println("Libros prestados por " + client.getProfile().getName() + " " + client.getProfile().getLastName() + ":");
            for (Book book : borrowedBooks) {
                System.out.println("- Título: " + book.getTitle() + ", ISBN: " + book.getIsbn());
            }
        }
    }   
    System.out.println();
    }

    
    
    
    public void Eliminarlibro() {
          System.out.println("");                
    System.out.println("Listado de todos los libros:");
    ArrayList<Book> boooks = library.getBooks();
    if (boooks.isEmpty()) {
        System.out.println("No hay libros disponibles en la biblioteca.\n");
        return;
    }
    for (Book book : boooks) {
        if (book.getIsAvailable()) {
        System.out.println("ISBN: " + book.getIsbn() + ", Título: " + book.getTitle());
        }
    }
    System.out.println();               
    System.out.print("Ingrese el ISBN del libro que desea eliminar: ");
    String isbnToDelete = scanner.nextLine();
    Book bookToDelete = library.readBook(isbnToDelete);
    if (bookToDelete == null) {
        System.out.println("El libro con ISBN " + isbnToDelete + " no existe en la biblioteca.");
    } else {
        if (!bookToDelete.getIsAvailable()) {
            System.out.println("El libro con ISBN " + isbnToDelete + " está en poder de un cliente y no puede ser eliminado.");
        } else {
            library.deleteBook(isbnToDelete);
            System.out.println("El libro con ISBN " + isbnToDelete + " ha sido eliminado correctamente.");
        }
    }

    }

    
    
    
    public void Prestamosydevolucionesdelibros() {
       System.out.println(""); 
    ArrayList<Book> availableBoks = new ArrayList<>();
    for (Book book : library.getBooks()) {
        if (book.getIsAvailable()) {
            availableBoks.add(book);
        }
    }
    System.out.println("Libros disponibles:");
    for (int i = 0; i < availableBoks.size(); i++) {
        System.out.println((i + 1) + ". ISBN: " + availableBoks.get(i).getIsbn() + ", Título: " + availableBoks.get(i).getTitle());
    }

 
    System.out.println("\nClientes disponibles:");
    ArrayList<Client> availableClients = library.getClients();
    for (int i = 0; i < availableClients.size(); i++) {
        System.out.println((i + 1) + ". Nombre: " + availableClients.get(i).getProfile().getName() + " " + availableClients.get(i).getProfile().getLastName());
    }


    System.out.println("\n¿Qué acción desea realizar?");
    System.out.println("1. Prestar libro");
    System.out.println("2. Devolver libro");
    System.out.print("Seleccione una opción: ");
    String actionOption = scanner.nextLine();

    switch (actionOption) {
       case "1":
        System.out.println("");      
    System.out.println("Préstamo de libro:");
   
    System.out.println("Clientes disponibles:");
    ArrayList<Client> availableClient = library.getClients();
    if (availableClient.isEmpty()) {
        System.out.println("No hay clientes disponibles en la biblioteca. Debe agregar al menos un cliente primero.\n");
        break;
    }
    for (int i = 0; i < availableClient.size(); i++) {
        Client client = availableClient.get(i);
        System.out.println((i+1) + ". " + client.getProfile().getName() + " " + client.getProfile().getLastName());
    }
    
   
    System.out.print("Seleccione el cliente al que desea prestar el libro (ingrese el número correspondiente): ");
    int clientIndex = Integer.parseInt(scanner.nextLine()) - 1;
    if (clientIndex < 0 || clientIndex >= availableClients.size()) {
        System.out.println("Opción inválida.\n");
        break;
    }
    Client selectedClient = availableClients.get(clientIndex);
    
  
    if (selectedClient.getBorrowedBooks().size() >= 3) {
        System.out.println("El cliente ya tiene 3 libros prestados y no puede pedir prestado más.\n");
        break;
    }
    

    System.out.println("Libros disponibles:");
    ArrayList<Book> availableeBooks = library.getBooks();
    if (availableeBooks.isEmpty()) {
        System.out.println("No hay libros disponibles en la biblioteca.\n");
        break;
    }
    for (int i = 0; i < availableeBooks.size(); i++) {
        Book book = availableeBooks.get(i);
        if (book.getIsAvailable()) {
            System.out.println((i+1) + ". " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
        }
    }
    
    
    System.out.print("Seleccione el libro que desea prestar (ingrese el número correspondiente): ");
    int bookIndexx = Integer.parseInt(scanner.nextLine()) - 1;
    if (bookIndexx < 0 || bookIndexx >= availableeBooks.size()) {
        System.out.println("Opción inválida.\n");
        break;
    }
    Book selectedBok = availableeBooks.get(bookIndexx);
    
  
    library.borrowBook(selectedClient, selectedBok);
    System.out.println("Libro prestado correctamente.\n");
    break;

        case "2":
     System.out.println(""); 
System.out.println("\nClientes que tienen libros en su poder:");
for (int i = 0; i < availableClients.size(); i++) {
    Client client = availableClients.get(i);
    if (!client.getBorrowedBooks().isEmpty()) {
        System.out.println((i + 1) + ". Nombre: " + client.getProfile().getName() + " " + client.getProfile().getLastName());
    }
}


System.out.print("Seleccione el número de cliente que va a devolver un libro: ");
int clentIndex = Integer.parseInt(scanner.nextLine()) - 1;
if (clentIndex < 0 || clentIndex >= availableClients.size()) {
    System.out.println("Número de cliente inválido.");
    break;
}


Client selectedClent = availableClients.get(clentIndex);


ArrayList<Book> borrowedBooks = selectedClent.getBorrowedBooks();
System.out.println("\nLibros prestados por " + selectedClent.getProfile().getName() + " " + selectedClent.getProfile().getLastName() + ":");
for (int i = 0; i < borrowedBooks.size(); i++) {
    Book borrowedBook = borrowedBooks.get(i);
    System.out.println((i + 1) + ". ISBN: " + borrowedBook.getIsbn() + ", Título: " + borrowedBook.getTitle());
}


System.out.print("Seleccione el número de libro que va a devolver: ");
int bokIndex = Integer.parseInt(scanner.nextLine()) - 1;
if (bokIndex < 0 || bokIndex >= borrowedBooks.size()) {
    System.out.println("Número de libro inválido.");
    break;
}


Book selectedBokk = borrowedBooks.get(bokIndex);


library.returnBook(selectedClent, selectedBokk);
System.out.println("El libro ha sido devuelto correctamente.");


selectedBokk.setIsAvailable(true);

            break;
        default:
            System.out.println("Opción no válida.");
    }

    }
    
    
    

    public void Generarreportedemovimientos() {
      System.out.println(""); 
                System.out.println("Generar reporte:");
                System.out.println("1. Por libro");
                System.out.println("2. Por cliente");
                System.out.println("3. Por fecha");
                System.out.print("Seleccione una opción: ");
                int reportOption = scanner.nextInt();
                scanner.nextLine(); 

                switch (reportOption) {
                    case 1:
                          System.out.println(""); 
                        System.out.print("Ingrese el ISBN del libro: ");
                        String bookISBN = scanner.nextLine();
                        Book book = library.readBook(bookISBN);
                        if (book != null) {
                            ArrayList<Transaction> bookReport = library.generateBookReport(book);
                            library.displayReport(bookReport);
                        } else {
                            System.out.println("El libro especificado no existe.");
                        }
                        break;
                   case 2:
                        ArrayList<Client> clientts = library.getClients();
                        System.out.println("Clientes disponibles:");
                        for (int i = 0; i < clientts.size(); i++) {
                            System.out.println((i + 1) + ". " + clientts.get(i).getProfile().getName());
                        }
                        System.out.print("Seleccione el número del cliente: ");
                        int clientNumber = scanner.nextInt();
                        scanner.nextLine(); 
                        if (clientNumber >= 1 && clientNumber <= clientts.size()) {
                            Client selectedClient = clientts.get(clientNumber - 1);
                            ArrayList<Transaction> clientReport = library.generateReportByClient(selectedClient);
                            library.displayReport(clientReport);
                        } else {
                            System.out.println("Número de cliente inválido.");
                        }
                        break;
                    case 3:
                          System.out.println(""); 
                        System.out.print("Ingrese la fecha de inicio (dd/mm/yyyy): ");
                        String startDateStr = scanner.nextLine();
                        System.out.print("Ingrese la fecha de fin (dd/mm/yyyy): ");
                        String endDateStr = scanner.nextLine();
                        try {
                            SimpleDateFormat dateFormaT = new SimpleDateFormat("dd/MM/yyyy");
                            Date startDate = dateFormaT.parse(startDateStr);
                            Date endDate = dateFormaT.parse(endDateStr);
                            ArrayList<Transaction> dateReport = library.generateReportByDate(startDate, endDate);
                            library.displayReport(dateReport);
                        } catch (ParseException e) {
                            System.out.println("Error: Formato de fecha incorrecto. Use dd/mm/yyyy.");
                        }
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }

    }
    
    
    

    public void Eliminarautor() {
          System.out.println("");
    System.out.println("Eliminar autor:");
    ArrayList<Author> authorsListToDelete = library.getAuthors();
    if (authorsListToDelete.isEmpty()) {
        System.out.println("No hay autores en la biblioteca.\n");
    } else {
        System.out.println("Lista de autores:");
        for (int i = 0; i < authorsListToDelete.size(); i++) {
            Author author = authorsListToDelete.get(i);
            System.out.println((i + 1) + ". " + author.getProfile().getName() + " " + author.getProfile().getLastName());
        }
        System.out.print("Seleccione el número del autor a eliminar: ");
        int authorIndexToDelete = Integer.parseInt(scanner.nextLine()) - 1;
        if (authorIndexToDelete >= 0 && authorIndexToDelete < authorsListToDelete.size()) {
            Author authorToDelete = authorsListToDelete.get(authorIndexToDelete);
            if (!authorToDelete.getBooks().isEmpty()) {
                System.out.println("El autor tiene libros escritos y no puede ser eliminado.");
            } else {
                library.deleteAuthor(authorToDelete.getProfile().getName());
                System.out.println("Autor eliminado correctamente.");
            }
        } else {
            System.out.println("Selección inválida.");
        }
    }

    }
    
    
    
    

    public void CRUDparaSuperadmin() {
        boolean running = true;
        while (running) {
            System.out.println("Menú:");
            System.out.println("1. Crear administrador");
            System.out.println("2. Actualizar administrador");
            System.out.println("3. Leer administradores");
            System.out.println("4. Eliminar administrador");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    library.CRUDcreateAdministrator();
                    break;
                case "2":
                    library.CRUDupdateAdministrator();
                    break;
                case "3":
                    library.CRUDreadAdministrators();
                    break;
                case "4":
                    library.CRUDdeleteAdministrator();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        }
        
        System.out.println("Menú del super administrador");
 
    }
    
    
    
       public void Salir() {
        exit = true;
    }
       
       
       
 public void Vertranssacciones() {
       System.out.println("Seleccione una opción:");
        System.out.println("1. Filtrar transacciones por fecha");
        System.out.println("2. Mostrar todas las transacciones");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
        
        switch (opcion) {
            case 1:
              System.out.print("Ingrese su nombre de usuario: ");
 String usernamet = scanner.nextLine();

Client selectedClient = null;
for (Client client : library.getClients()) {
    if (client.getUsername().equals(usernamet)) {
        selectedClient = client;
        break;
    }
}

if (selectedClient != null) {
   
    System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
    String fechaInicio = scanner.nextLine();
    System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
    String fechaFin = scanner.nextLine();
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
        Date startDate = dateFormat.parse(fechaInicio);
        Date endDate = dateFormat.parse(fechaFin);
        
      
        ArrayList<Transaction> clientReport = library.generateReportByClientAndDate(selectedClient, startDate, endDate);
        library.displayReport(clientReport);
    } catch (ParseException e) {
        System.out.println("Error al parsear las fechas.");
    }
} else {
    System.out.println("No se encontró ningún cliente con ese nombre de usuario.");
}
break;
            case 2:
                System.out.print("Ingrese su nombre de usuario: ");
                String usernamed = scanner.nextLine();

                Client selectedClientAll = null;
                for (Client client : library.getClients()) {
                    if (client.getUsername().equals(usernamed)) {
                        selectedClientAll = client;
                        break;
                    }
                }

                if (selectedClientAll != null) {
                    ArrayList<Transaction> allClientReport = library.generateReportByClient(selectedClientAll);
                    library.displayReport(allClientReport);
                } else {
                    System.out.println("No se encontró ningún cliente con ese nombre de usuario.");
                }
                break;
            default:
                System.out.println("Opción inválida.");
        }

    }
 
 
 
    public boolean isExit() {
        return exit;
    }
    
    
    
    
    public void Menu1() {
      System.out.println("");
            System.out.println("");
            System.out.println("Por favor, selecciona una opción:");
            System.out.println("1. Mostrar todos los libros disponibles");
            System.out.println("2. Actualizar libro");
            System.out.println("3. Listado de todos los autores");
            System.out.println("4. Añadir autor");
            System.out.println("5. Añadir nuevo libro");
            System.out.println("6. Añadir nuevo cliente");
            System.out.println("7. Actualizar Autor");
            System.out.println("8.  Eliminar cliente");
            System.out.println("9.  Mostrar libros prestados");
            System.out.println("10. Eliminar libro");
            System.out.println("11. Prestamos y devoluciones de libros ");
            System.out.println("12. Generar reporte de movimientos");
            System.out.println("13. Eliminar autor");
            System.out.println("14. CRUD para Superadmin");
            System.out.println("15. Salir");
            System.out.print("Seleccione una opción: ");   
      
}
   public void Menu2() {
      System.out.println("");
         System.out.println("");
            System.out.println("");
            System.out.println("Por favor, selecciona una opción:");
            System.out.println("1. Mostrar todos los libros disponibles");
            System.out.println("2. Actualizar libro");
            System.out.println("3. Listado de todos los autores");
            System.out.println("4. Añadir autor");
            System.out.println("5. Añadir nuevo libro");
            System.out.println("6. Añadir nuevo cliente");
            System.out.println("7. Actualizar Autor");
            System.out.println("8.  Eliminar cliente");
            System.out.println("9.  Mostrar libros prestados");
            System.out.println("10. Eliminar libro");
            System.out.println("11. Prestamos y devoluciones de libros ");
            System.out.println("12. Generar reporte de movimientos");
            System.out.println("13. Eliminar autor");
            System.out.println("14. Salir");
            System.out.print("Seleccione una opción: ");  
      
}
   
public void Menu3() {
    System.out.println("");
    System.out.println("");
    System.out.println("1. Ver libros disponibles");
    System.out.println("2. Ver transacciones");
    System.out.println("3. Salir");
    System.out.print("Seleccione una opción: ");  
      
}

}

    
    

