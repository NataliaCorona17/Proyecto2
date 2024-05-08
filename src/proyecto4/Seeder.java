/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto4;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Seeder {

    public static CRUD initialize() throws ParseException {
        CRUD library = new CRUD();
      
        Profile authorProfile1 = new Profile("Pedro", "Paramo", new Date());
        Profile authorProfile2 = new Profile("Juan", "Alfonso", new Date());
        Profile authorProfile3 = new Profile("J.K.", "Rowling", new Date());
        Profile authorProfile4 = new Profile("Stephen", "King", new Date());
        Profile authorProfile5 = new Profile("Gabriel", "García Márquez", new Date());
        Profile authorProfile6 = new Profile("Jane", "Austen", new Date());
        Profile authorProfile7 = new Profile("Fyodor", "Dostoevsky", new Date());
        Profile authorProfile8 = new Profile("George", "Orwell", new Date());
        Profile authorProfile9 = new Profile("Haruki", "Murakami", new Date());
        Profile authorProfile10 = new Profile("Leo", "Tolstoy", new Date());

        
        Author author1 = new Author(authorProfile1, new ArrayList<>());
        Author author2 = new Author(authorProfile2, new ArrayList<>());
        Author author3 = new Author(authorProfile3, new ArrayList<>());
        Author author4 = new Author(authorProfile4, new ArrayList<>());
        Author author5 = new Author(authorProfile5, new ArrayList<>());
        Author author6 = new Author(authorProfile6, new ArrayList<>());
        Author author7 = new Author(authorProfile7, new ArrayList<>());
        Author author8 = new Author(authorProfile8, new ArrayList<>());
        Author author9 = new Author(authorProfile9, new ArrayList<>());
        Author author10 = new Author(authorProfile10, new ArrayList<>());

      
        Book book1 = new Book("1231", "1927", author1, new Date(), true);
        Book book2 = new Book("1232", "Manual", author2, new Date(), true);
        Book book3 = new Book("8433", "Harry Potter", author3, new Date(), true);
        Book book4 = new Book("0864", "The Shining", author4, new Date(), true);
        Book book5 = new Book("3745", "Cien años de soledad", author5, new Date(), true);
        Book book6 = new Book("2316", "Orgullo y prejuicio", author6, new Date(), true);
        Book book7 = new Book("1117", "Crime and Punishment", author7, new Date(), true);
        Book book8 = new Book("2228", "1984", author8, new Date(), true);
        Book book9 = new Book("3339", "Kafka on the Shore", author9, new Date(), true);
        Book book10 = new Book("4440", "War and Peace", author10, new Date(), true);

  
        Profile clientProfile = new Profile("Juan", "Hernandez", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));
        Client client = new Client(clientProfile, "juan", "1234", new ArrayList<>());
    
        
        Profile clientProfile2 = new Profile("Jesus", "Jose", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));
        Client client2 = new Client(clientProfile2, "Pedro", "4321", new ArrayList<>());
    
       
        
        
        
          Profile adminProfile = new Profile("Nombre", "Apellido", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));
    
        ArrayList<Permissions> adminPermissions = new ArrayList<>();
        adminPermissions.add(Permissions.READ);
        adminPermissions.add(Permissions.WRITE);
        adminPermissions.add(Permissions.DELETE);
   
        Administrator admin = new Administrator(adminPermissions, true, adminProfile, "admin", "peru");

     
       

        
    Profile newAdminProfile = new Profile("Juan", "Perez", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000"));


    ArrayList<Permissions> newAdminPermissions = new ArrayList<>();
    newAdminPermissions.add(Permissions.READ);
    newAdminPermissions.add(Permissions.WRITE);
    adminPermissions.add(Permissions.DELETE);

Administrator newAdmin = new Administrator(newAdminPermissions, false, newAdminProfile, "admin2", "panama");
        library.createAuthor(author1);
        library.createAuthor(author2);
        library.createAuthor(author3);
        library.createAuthor(author4);
        library.createAuthor(author5);
        library.createAuthor(author6);
        library.createAuthor(author7);
        library.createAuthor(author8);
        library.createAuthor(author9);
        library.createAuthor(author10);

        library.createBook(book1);
        library.createBook(book2);
        library.createBook(book3);
        library.createBook(book4);
        library.createBook(book5);
        library.createBook(book6);
        library.createBook(book7);
        library.createBook(book8);
        library.createBook(book9);
        library.createBook(book10);
        library.createClients(client);
        library.createClients(client2);
        library.createAdministrator(newAdmin);
        library.createAdministrator(admin);        
       

     
        System.out.println("Datos iniciales creados correctamente.");
        
        
        return library;
    

}}













