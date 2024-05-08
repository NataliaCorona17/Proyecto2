
package proyecto4;

import java.util.ArrayList;

class Author {
    private Profile profile;
    private ArrayList<Book> books;

    public Author(Profile profile, ArrayList<Book> books) {
        this.profile = profile;
        this.books = books;
    }

   
    public Profile getProfile() {
        return profile;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
