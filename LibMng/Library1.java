import java.io.*;
import java.util.*;

class Book implements Serializable {
    private String title, author, ISBN;
    private boolean isBorrowed;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isBorrowed = false;
    }

    public String getTitle() { 
        return title;
     }
     public String getAuthor(){
        return this.author;
     }
    public String getISBN() { 
        return ISBN;
    }
    public boolean isBorrowed() {
        return isBorrowed; 
    }
    
    public void borrowBook() { 
        this.isBorrowed = true; 
    }
    public void returnBook() {
        this.isBorrowed = false; 
    }
    
    public String toString() {
        return "Book Name : " + title + ", Author : " + author + ", Book Number : " + ISBN;
    }
}

class User {
    private String name, userID;
    private List<Book> borrowedBooks;

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserID() {
         return userID;
    }
    public String getUserName(){
        return name;
    }
    public List<Book> getBorrowedBooks() { 
        return borrowedBooks; 
    }

    public void borrowBook(Book b) { 
        borrowedBooks.add(b); 
    }
    public void returnBook(Book b) { 
        borrowedBooks.remove(b); 
    }

    public String toString() {
        return "User: " + name + ", ID: " + userID;
    }
}

class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) { 
        super(message); 
    }
}

class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) { 
        super(message); 
    }
}

interface ILibrary {
    void borrowBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;
    void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException;
    Book searchBook(String title);
}

abstract class LibrarySystem implements ILibrary {
    protected List<Book> books = new ArrayList<>();
    protected List<User> users = new ArrayList<>();

    public void addBook(Book b) { 
        books.add(b); 
    }
    public void addUser(User u) { 
        users.add(u); 
    }
}

class LibraryManager extends LibrarySystem {
    
    public synchronized void borrowBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
        Book book = books.stream().filter(b -> b.getISBN().equals(ISBN) && !b.isBorrowed()).findFirst().orElse(null);
        if (book == null) throw new BookNotFoundException("Book not found.");

        User user = users.stream().filter(u -> u.getUserID().equals(userID)).findFirst().orElse(null);
        if (user == null) throw new UserNotFoundException("User not found.");

        book.borrowBook();
        user.borrowBook(book);
        System.out.println(userID + " borrowed " + book.getTitle());
    }

    public synchronized void returnBook(String ISBN, String userID) throws BookNotFoundException, UserNotFoundException {
        User user = users.stream().filter(u -> u.getUserID().equals(userID)).findFirst().orElse(null);
        if (user == null) throw new UserNotFoundException("User not found!");

        Book book = user.getBorrowedBooks().stream().filter(b -> b.getISBN().equals(ISBN)).findFirst().orElse(null);
        if (book == null) throw new BookNotFoundException("Book not found.");

        book.returnBook();
        user.returnBook(book);
        System.out.println(userID + " returned " + book.getTitle());
    }

    public Book searchBook(String title) {
        return books.stream().filter(b -> b.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }

    public void saveLibraryData() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libraryData.txt"))) {
            oos.writeObject(books);
        }
    }

    public void loadLibraryData() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("libraryData.txt"))) {
            books = (List<Book>) ois.readObject();
        }
    }
}

public class Library1{
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();

        library.addBook(new Book("Java", "James Gosling", "12345"));
        library.addBook(new Book("Python", "Guido van Rossum", "67890"));

        library.addUser(new User("Saravanan", "PEC1"));
        library.addUser(new User("Kumar", "PEC2"));

        Thread t1 = new Thread(() -> {
            try { library.borrowBook("12345", "PEC1"); } 
            catch (Exception e) { System.out.println(e.getMessage()); }
        });

        Thread t2 = new Thread(() -> {
            try { library.borrowBook("67890", "PEC2"); } 
            catch (Exception e) { System.out.println(e.getMessage()); }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        try {
            library.saveLibraryData();
            System.out.println("Library data saved.");
        } catch (IOException e) {
            System.out.println("Error :  " + e.getMessage());
        }
    }
}













