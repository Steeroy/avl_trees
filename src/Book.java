
public class Book {

    private String title;
    private int id;
    private String author;
    private double price;

    public Book() {
        this.title = "";
        this.id = 0;
        this.author = "";
        this.price = 0.0;
    }

    public Book(int id) {
        this.title = "";
        this.id = id;
        this.author = "";
        this.price = 0.0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book(String title, int id, String author, double price) {
        this.title = title;
        this.id = id;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book object\n{" + "title= " + title + ", id= " + id + ", author= " + author + ", price= " + price + '}';
    }

}
