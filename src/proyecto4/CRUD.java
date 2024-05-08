/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto4;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

class CRUD {
     Scanner scanner=new Scanner(System.in);
    private ArrayList<Book> books;
    private ArrayList<Client> clients;
    private ArrayList<Author> authors;
    private ArrayList<Transaction> transactions;
    private ArrayList<Administrator> administrators = new ArrayList<>();
    
    public CRUD() {
        this.books = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
     
    void createBook(Book book) {
        if (book.getAuthor() != null && authors.contains(book.getAuthor())) {
            books.add(book);
            book.getAuthor().getBooks().add(book);
        } else {
            System.out.println("El autor no existe.");
        }
    }

    Book readBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    void updateBook(Book book) {
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
                b.setPublishDate(book.getPublishDate());
                b.setIsAvailable(book.getIsAvailable());
            }
        }
    }
    
    
    
    
    
  public void createAdministrator(Administrator admin) {
    
        for (Administrator existingAdmin : this.administrators) {
            if (existingAdmin.getUsername().equals(admin.getUsername())) {
                System.out.println("Ya existe un administrador con ese nombre de usuario.");
                return;
            }
        }
        this.administrators.add(admin);
        System.out.println("Administrador creado exitosamente.");
    }
  
  
  
  
public User logind(String username, String password) {
    for (Client client : clients) {
        if (client.getUsername().equals(username) && client.checkPassword(password)) {
            return client;
        }
    }
    for (Administrator admin : administrators) {
        if (admin.getUsername().equals(username) && admin.checkPassword(password)) {
            return admin;
        }
    }
    System.out.println("Inicio de sesión fallido. Nombre de usuario o contraseña incorrectos.");
    return null;
}

  
  
    void deleteBook(String isbn) {
        Book bookToDelete = null;
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.getIsAvailable()) {
                    System.out.println("El libro está en poder de un cliente y no puede ser eliminado.");
                    return;
                }
                bookToDelete = book;
                break;
            }
        }
        if (bookToDelete != null) {
            books.remove(bookToDelete);
            bookToDelete.getAuthor().getBooks().remove(bookToDelete);
        }
    }
    
    
    
 public void createClients(Client client) {
     
        for (Client existingClient : this.clients) {
            if (existingClient.getUsername().equals(client.getUsername())) {
                System.out.println("Ya existe un cliente con ese nombre de usuario.");
                return;
            }
        }
        client.setPassword(client.hashPassword(client.getPassword()));
        this.clients.add(client);
        System.out.println("Cliente creado exitosamente.");
    }     
 



    void deleteClient(String clientId) {
        Client clientToDelete = null;
        for (Client client : clients) {
            if (client.getProfile().getName().equals(clientId)) {
                if (!client.getBorrowedBooks().isEmpty()) {
                    System.out.println("El cliente tiene libros en su poder y no puede ser eliminado.");
                    return;
                }
                clientToDelete = client;
                break;
            }
        }
        if (clientToDelete != null) {
            clients.remove(clientToDelete);
        }
    }
    
    
   
    void createAuthor(Author author) {
        authors.add(author);
    }

    

    void updateAuthor(Author author) {
        for (Author a : authors) {
            if (a.getProfile().getName().equals(author.getProfile().getName())) {
                a.setProfile(author.getProfile());
                a.setBooks(author.getBooks());
            }
        }
    }
    
    

    void deleteAuthor(String authorId) {
        Author authorToDelete = null;
        for (Author author : authors) {
            if (author.getProfile().getName().equals(authorId)) {
                if (!author.getBooks().isEmpty()) {
                    System.out.println("El autor tiene libros escritos y no puede ser eliminado.");
                    return;
                }
                authorToDelete = author;
                break;
            }
        }
        if (authorToDelete != null) {
            authors.remove(authorToDelete);
        }
    }
    
    
    
    
          void borrowBook(Client client, Book book) {
    if (client.getBorrowedBooks().size() >= 3) {
        System.out.println("El cliente ya tiene 3 libros prestados y no puede pedir prestado más.");
        return;
    }
    if (!book.getIsAvailable()) {
        System.out.println("El libro no está disponible para préstamo.");
        return;
    }
    book.setIsAvailable(false);
    client.getBorrowedBooks().add(book);
    Date currentDate = new Date();  
    Transaction transaction = new Transaction(UUID.randomUUID().toString(), "Borrow", client, book, currentDate);
    transactions.add(transaction);
}


          
    void returnBook(Client client, Book book) {
    if (!client.getBorrowedBooks().contains(book)) {
        System.out.println("El cliente no tiene este libro en su poder.");
        return;
    }
    book.setIsAvailable(true);
    client.getBorrowedBooks().remove(book);
    Date currentDate = new Date();  
    Transaction transaction = new Transaction(UUID.randomUUID().toString(), "Return", client, book, currentDate);
    transactions.add(transaction);
}


 

     public Client login(String username, String password) {
        for (Client client : clients) {
            if (client.getUsername().equals(username) && client.checkPassword(password)) {
                return client;
            }
        }
        return null;
    }

   
     
    ArrayList<Transaction> generateBookReport(Book book) {
        ArrayList<Transaction> report = new ArrayList<>();
        if (book == null) {
            System.out.println("Libro no especificado.");
            return report;
        }
        Date publishDate = book.getPublishDate();
        if (publishDate == null) {
            System.out.println("Fecha de publicación del libro no disponible.");
            return report;
        }
        Date currentDate = new Date();
        for (Transaction transaction : transactions) {
            if (transaction.getBook().getIsbn().equals(book.getIsbn())
                    && transaction.getDate().after(publishDate)
                    && transaction.getDate().before(currentDate)) {
                report.add(transaction);
            }
        }
        return report;
    }
    
    

    ArrayList<Transaction> generateReportByClient(Client client) {
        ArrayList<Transaction> report = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getClient() != null && transaction.getClient().equals(client)) {
                report.add(transaction);
            }
        }
        return report;
    }

   
    ArrayList<Transaction> generateReportByDate(Date startDate, Date endDate) {
        ArrayList<Transaction> report = new ArrayList<>();
        for (Transaction transaction : transactions) {
            Date transactionDate = transaction.getDate();
            if (transactionDate != null && !transactionDate.before(startDate) && !transactionDate.after(endDate)) {
                report.add(transaction);
            }
        }
        return report;
    }
    
    
    ArrayList<Transaction> generateReportByClientAndDate(Client client, Date startDate, Date endDate) {
    ArrayList<Transaction> report = new ArrayList<>();
    for (Transaction transaction : transactions) {
        Date transactionDate = transaction.getDate();
        if (transaction.getClient() != null && transaction.getClient().equals(client) &&
            transactionDate != null && !transactionDate.before(startDate) && !transactionDate.after(endDate)) {
            report.add(transaction);
        }
    }
    return report;
}

    
    
    
    
void displayReport(ArrayList<Transaction> report) {
    if (report.isEmpty()) {
        System.out.println("No hay movimientos que mostrar en el reporte.");
        return;
    }
    System.out.println("Reporte de movimientos:");
    for (Transaction transaction : report) {
        String type = transaction.getType();
        String clientName = (transaction.getClient() != null) ? transaction.getClient().getProfile().getName() : "Cliente no especificado";
        String bookTitle = (transaction.getBook() != null) ? transaction.getBook().getTitle() : "Libro no especificado";
        String date = transaction.getDate().toString(); 
        System.out.println("Tipo: " + type + ", Cliente: " + clientName + ", Libro: " + bookTitle + ", Fecha: " + date);
    }
}


    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

  
    
    public void addUser(User user) {
    if (user instanceof Administrator) {
        createAdministrator((Administrator) user);
    } else if (user instanceof Client) {
        createClients((Client) user);
    }
}

public User getUserByUsername(String username) {
    for (Administrator admin : administrators) {
        if (admin.getUsername().equals(username)) {
            return admin;
        }
    }

    for (Client client : clients) {
        if (client.getUsername().equals(username)) {
            return client;
        }
    }

    return null;
}
    
    
    
    
    
    
   
    
public void CRUDcreateAdministrator() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo administrador:");
        String name = scanner.nextLine();

        System.out.println("Ingrese el apellido del nuevo administrador:");
        String lastName = scanner.nextLine();

        System.out.println("Ingrese la fecha de nacimiento del nuevo administrador (en formato dd/MM/yyyy):");
        String birthDateString = scanner.nextLine();

        Profile newAdminProfile;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date birthdate = dateFormat.parse(birthDateString);
            newAdminProfile = new Profile(name, lastName, birthdate);
        } catch (ParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Use el formato dd/MM/yyyy.");
            return;
        }

        ArrayList<Permissions> newAdminPermissions = new ArrayList<>();
        newAdminPermissions.add(Permissions.READ);
        newAdminPermissions.add(Permissions.WRITE);
        newAdminPermissions.add(Permissions.DELETE);

        System.out.println("Ingrese el nombre de usuario del nuevo administrador:");
        String username = scanner.nextLine();

        System.out.println("Ingrese la contraseña del nuevo administrador:");
        String password = scanner.nextLine();

        Administrator newAdmin = new Administrator(newAdminPermissions, false, newAdminProfile, username, password);
        administrators.add(newAdmin);

        System.out.println("Administrador creado correctamente.");
    }




    public void CRUDupdateAdministrator() {
    System.out.println("Lista de administradores normales:");
    ArrayList<Administrator> allAdministrators = getAdministrators();
    ArrayList<Administrator> normalAdministrators = new ArrayList<>();
    for (Administrator admin : allAdministrators) {
        if (!admin.isIsSuperAdmin()) {
            normalAdministrators.add(admin);
        }
    }
    for (int i = 0; i < normalAdministrators.size(); i++) {
        Administrator admin = normalAdministrators.get(i);
        System.out.println((i + 1) + ". " + admin.getProfile().getName() + " " + admin.getProfile().getLastName());
    }
    System.out.print("Seleccione el número del administrador que desea editar: ");
    int adminIndex = Integer.parseInt(scanner.nextLine()) - 1;
    if (adminIndex < 0 || adminIndex >= normalAdministrators.size()) {
        System.out.println("Índice de administrador inválido.");
        return;
    }
    Administrator adminToEdit = normalAdministrators.get(adminIndex);
    System.out.println("Ingrese los nuevos detalles para el administrador:");
    System.out.print("Nuevo nombre: ");
    String newName = scanner.nextLine();
    System.out.print("Nuevo apellido: ");
    String newLastName = scanner.nextLine();
    System.out.print("Nueva fecha de nacimiento (dd/MM/yyyy): ");
    Date newDateOfBirth = null;
    try {
        newDateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
    } catch (ParseException e) {
        System.out.println("Formato de fecha inválido. Utilice el formato dd/MM/yyyy.");
        return;
    }
    Profile updatedProfile = new Profile(newName, newLastName, newDateOfBirth);
    adminToEdit.setProfile(updatedProfile);
    for (int i = 0; i < allAdministrators.size(); i++) {
        if (allAdministrators.get(i).getUsername().equals(adminToEdit.getUsername())) {
            allAdministrators.set(i, adminToEdit);
            break;
        }
    }
    System.out.println("Administrador actualizado correctamente.");
}






  public void CRUDdeleteAdministrator() {
    System.out.println("Lista de administradores normales:");
    ArrayList<Administrator> allAdministrators = getAdministrators();
    ArrayList<Administrator> normalAdministrators = new ArrayList<>();
    for (Administrator admin : allAdministrators) {
        if (!admin.isIsSuperAdmin()) {
            normalAdministrators.add(admin);
        }
    }
    for (int i = 0; i < normalAdministrators.size(); i++) {
        Administrator admin = normalAdministrators.get(i);
        System.out.println((i + 1) + ". " + admin.getProfile().getName() + " " + admin.getProfile().getLastName());
    }
    System.out.print("Seleccione el número del administrador que desea eliminar: ");
    int adminIndex = Integer.parseInt(scanner.nextLine()) - 1;
    if (adminIndex < 0 || adminIndex >= normalAdministrators.size()) {
        System.out.println("Índice de administrador inválido.");
        return;
    }
    Administrator adminToDelete = normalAdministrators.get(adminIndex);
    normalAdministrators.remove(adminIndex);
    for (int i = 0; i < allAdministrators.size(); i++) {
        if (allAdministrators.get(i).getUsername().equals(adminToDelete.getUsername())) {
            allAdministrators.remove(i);
            break;
        }
    }
    System.out.println("Administrador eliminado correctamente.");
}





   
    
    public ArrayList<Administrator> getAdministrators() {
        return administrators;
    }
         public void CRUDreadAdministrators() {
    System.out.println("Lista de administradores normales:");
    ArrayList<Administrator> allAdministrators = getAdministrators(); 
    ArrayList<Administrator> normalAdministrators = new ArrayList<>();
    for (Administrator admin : allAdministrators) {
        if (!admin.isIsSuperAdmin()) {
            normalAdministrators.add(admin);
        }
    } 
    for (Administrator admin : normalAdministrators) {
        System.out.println("Nombre: " + admin.getProfile().getName());
        System.out.println("Apellido: " + admin.getProfile().getLastName());
        System.out.println("Username: " + admin.getUsername());
        System.out.println("----------------------------------");
    }
}

}


    





