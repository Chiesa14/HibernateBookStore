package Controllers;

import Models.Book;
import Models.Library;
import Models.Publisher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BookDAO;
import utils.LibraryDAO;
import utils.PublisherDAO;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/createBook")
public class CreateBookServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createBook.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisherName = request.getParameter("publisher");
        String libraryName = request.getParameter("library");
        String categoriesString = request.getParameter("categories");
        String tagsString = request.getParameter("tags");

        // Parse categories and tags
        Set<String> tags = parseTags(tagsString);

        // Create Publisher and Library objects
        PublisherDAO publisherDAO = new PublisherDAO();
        Publisher publisher = publisherDAO.getPublisherByName(publisherName);
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setName(publisherName);
        }

        // Check if the Library exists or create a new one
        LibraryDAO libraryDAO = new LibraryDAO();
        Library library = libraryDAO.getLibraryByName(libraryName);
        if (library == null) {
            library = new Library();
            library.setName(libraryName);
        }

        // Create Book object
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setLibrary(library);
        book.setTags(tags);

        // Insert book into the database
        BookDAO bookDAO = new BookDAO();
        bookDAO.createBook(book);

        // Redirect to home page or success page
        response.sendRedirect("home");
    }

    // Utility methods for parsing tags
    private Set<String> parseTags(String tagsString) {
        Set<String> tags = new HashSet<>();
        if (tagsString != null && !tagsString.isEmpty()) {
            tags.addAll(Arrays.asList(tagsString.split(",")));
        }
        return tags;
    }
}
