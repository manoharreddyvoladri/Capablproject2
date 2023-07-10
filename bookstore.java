import java.util.ArrayList;
import java.util.List;

class User {
    private String username;
    private String password;
    private String email;
    // Additional user profile information

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Getters and setters for user properties
}

class Book {
    private String title;
    private String author;
    private double price;
    private int stock;

    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    // Getters and setters for book properties
}

class ShoppingCart {
    private List<Book> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addBook(Book book) {
        items.add(book);
    }

    public void removeBook(Book book) {
        items.remove(book);
    }

    public List<Book> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }
}

class Order {
    private User user;
    private List<Book> books;
    private double totalAmount;
    // Additional order information like payment details, shipping address, etc.

    public Order(User user, List<Book> books) {
        this.user = user;
        this.books = books;
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        totalAmount = 0;
        for (Book book : books) {
            totalAmount += book.getPrice();
        }
    }

    // Getters for order properties

    public void generateInvoice() {
        // Logic to generate invoice
        System.out.println("Invoice generated successfully.");
    }

    public void processPayment() {
        // Logic to process payment
        System.out.println("Payment processed successfully.");
    }
}

class Bookstore {
    private List<User> users;
    private List<Book> catalog;
    private List<Order> orders;

    public Bookstore() {
        users = new ArrayList<>();
        catalog = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void registerUser(String username, String password, String email) {
        User user = new User(username, password, email);
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("User logged in successfully.");
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public void addBookToCatalog(String title, String author, double price, int stock) {
        Book book = new Book(title, author, price, stock);
        catalog.add(book);
        System.out.println("Book added to the catalog.");
    }

    public List<Book> getCatalog() {
        return catalog;
    }

    public void addToCart(ShoppingCart cart, Book book) {
        cart.addBook(book);
        System.out.println("Book added to the shopping cart.");
    }

    public void removeFromCart(ShoppingCart cart, Book book) {
        cart.removeBook(book);
        System.out.println("Book removed from the shopping cart.");
    }

    public void checkout(ShoppingCart cart, User user) {
        List<Book> items = cart.getItems();
        Order order = new Order(user, items);
        orders.add(order);
        cart.clearCart();
        order.generateInvoice();
        order.processPayment();
        System.out.println("Order placed successfully.");
    }
}

public class Main {
    public static void main(String[] args) {
        Bookstore bookstore = new Bookstore();

        // User registration
        bookstore.registerUser("johnDoe", "password123", "john@example.com");

        // User login
        User user = bookstore.loginUser("johnDoe", "password123");

        // Add books to catalog
        bookstore.addBookToCatalog("Book 1", "Author 1", 19.99, 10);
        bookstore.addBookToCatalog("Book 2", "Author 2", 14.99, 5);

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Add books to the shopping cart
        Book book1 = bookstore.getCatalog().get(0);
        bookstore.addToCart(cart, book1);

        Book book2 = bookstore.getCatalog().get(1);
        bookstore.addToCart(cart, book2);

        // Remove book from the shopping cart
        bookstore.removeFromCart(cart, book1);

        // Checkout
        bookstore.checkout(cart, user);
    }
}
