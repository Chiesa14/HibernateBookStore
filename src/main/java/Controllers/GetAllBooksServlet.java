package Controllers;

import Models.Book;
import Models.Library;
import Models.Publisher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BookDAO;
import utils.LibraryDAO;
import utils.PublisherDAO;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class GetAllBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from all models
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.getAllBooks();

        PublisherDAO publisherDAO = new PublisherDAO();
        List<Publisher> publishers = publisherDAO.getAllPublishers();

        LibraryDAO libraryDAO = new LibraryDAO();
        List<Library> libraries = libraryDAO.getAllLibraries();



        // Set data as attributes in the request
        request.setAttribute("books", books);
        request.setAttribute("publishers", publishers);
        request.setAttribute("libraries", libraries);

        // Forward the request to the JSP for rendering
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
