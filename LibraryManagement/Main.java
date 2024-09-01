
import java.util.Scanner;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Catalog System");
            System.out.println("1. Add a book");
            System.out.println("2. Search for books");
            System.out.println("3. List all books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    System.out.println("Book added successfully!");
                    break;
                case 2:
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    List<Book> results = library.searchBooks(query);
                    if (results.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        System.out.println("Search results:");
                        for (Book book : results) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 3:
                    List<Book> books = library.listBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books in the library.");
                    } else {
                        System.out.println("All books:");
                        for (Book book : books) {
                            System.out.println(book);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting from the Library");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}