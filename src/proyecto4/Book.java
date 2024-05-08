
package proyecto4;

import java.util.Date;
class Book {
    private String isbn;
    private String title;
    private Author author;
    private Date publishDate;
    private boolean isAvailable;

    public Book(String isbn, String title, Author author, Date publishDate, boolean isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.isAvailable = isAvailable;
    }

   
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

   
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

    
 
