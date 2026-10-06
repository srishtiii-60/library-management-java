import java.util.ArrayList;
import java.util.Scanner;

// Book class: ek book ki details rakhti hai
class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }
    public void setIssued(boolean issued) { this.issued = issued; }

    @Override
    public String toString() {
        String status = issued ? "Issued" : "Available";
        return id + " | " + title + " | " + author + " | " + status;
    }
}

// Library class: saari books ko manage karti hai
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private int nextId = 1;

    public void addBook(String title, String author) {
        books.add(new Book(nextId, title, author));
        System.out.println("Book added with ID " + nextId + "\n");
        nextId++;
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("Library mein abhi koi book nahi hai.\n");
            return;
        }
        System.out.println("\nID | Title | Author | Status");
        System.out.println("--------------------------------");
        for (Book b : books) {
            System.out.println(b);
        }
        System.out.println();
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public void issueBook(int id) {
        Book b = findBook(id);
        if (b == null) {
            System.out.println("Book nahi mili.\n");
        } else if (b.isIssued()) {
            System.out.println("Ye book pehle se issue hai.\n");
        } else {
            b.setIssued(true);
            System.out.println("Book issue ho gayi: " + b.getTitle() + "\n");
        }
    }

    public void returnBook(int id) {
        Book b = findBook(id);
        if (b == null) {
            System.out.println("Book nahi mili.\n");
        } else if (!b.isIssued()) {
            System.out.println("Ye book issue hi nahi thi.\n");
        } else {
            b.setIssued(false);
            System.out.println("Book return ho gayi: " + b.getTitle() + "\n");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== Library Management System ===");
            System.out.println("1. Add book");
            System.out.println("2. View all books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Exit");
            System.out.print("Choose (1-5): ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        library.addBook(title, author);
                        break;
                    case "2":
                        library.viewBooks();
                        break;
                    case "3":
                        System.out.print("Book ID: ");
                        library.issueBook(Integer.parseInt(sc.nextLine().trim()));
                        break;
                    case "4":
                        System.out.print("Book ID: ");
                        library.returnBook(Integer.parseInt(sc.nextLine().trim()));
                        break;
                    case "5":
                        System.out.println("Bye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Galat choice, dobara try karo.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Galat input! Number daalo.\n");
            }
        }
    }
}
