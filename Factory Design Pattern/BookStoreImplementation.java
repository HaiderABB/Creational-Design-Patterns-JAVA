import java.util.HashMap;
import java.util.Map;

interface Book {
  String getTitle();

  String getAuthor();

  double getPrice();
}

class FictionBook implements Book {
  private String title;
  private String author;
  private double price;

  public FictionBook(String title, String author, double price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getAuthor() {
    return author;
  }

  @Override
  public double getPrice() {
    return price;
  }
}

interface BookFactory {
  Book createBook(String title, String author, double price);

}

class FictionBookFactory implements BookFactory {
  @Override
  public Book createBook(String title, String author, double price) {
    return new FictionBook(title, author, price);
  }
}

class BookStore {
  private Map<String, BookFactory> factoryMap;

  public BookStore() {
    factoryMap = new HashMap<>();
    factoryMap.put("Fiction", new FictionBookFactory());
  }

  public void addBookFactory(String genre, BookFactory factory) {
    factoryMap.put(genre, factory);
  }

  public Book orderBook(String genre, String title, String author, double price) {
    BookFactory factory = factoryMap.get(genre);
    if (factory == null) {
      throw new IllegalArgumentException("No factory found for genre: " + genre);
    }
    return factory.createBook(title, author, price);
  }
}

public class BookStoreImplementation {
  public static void main(String[] args) {
    BookStore bookStore = new BookStore();
    bookStore.addBookFactory("Fiction", new FictionBookFactory());
    Book book = bookStore.orderBook("Fiction", "Harry Potter", "J.K Rowling", 78.56);
    System.out.println("Ordered Book:");
    System.out.println("Title: " + book.getTitle());
    System.out.println("Author: " + book.getAuthor());
    System.out.println("Price: $" + book.getPrice());

  }
}