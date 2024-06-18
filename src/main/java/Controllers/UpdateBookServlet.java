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
import utils.BookPage;
import utils.LibraryDAO;
import utils.PublisherDAO;

import java.io.IOException;

@WebServlet("/updatebook")
public class UpdateBookServlet extends HttpServlet {

    int BookId;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        BookPage bookPage = new BookPage();
        Book book = bookPage.getBookById(id);
        req.setAttribute("book", book);
        BookId = id;

        RequestDispatcher dispatcher = req.getRequestDispatcher("updateBook.jsp");
        dispatcher.forward(req, res);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = BookId;
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String publisherName = request.getParameter("publisher");
        String libraryName = request.getParameter("library");

        // Update the Publisher entity if necessary
        PublisherDAO publisherDAO = new PublisherDAO();
        Publisher publisher = publisherDAO.getPublisherByName(publisherName);
        if (publisher == null) {
            publisher = new Publisher();
            publisher.setName(publisherName);
            publisherDAO.createPublisher(publisher);
        } else {
            publisherDAO.updatePublisher(publisher);
        }

        // Update the Library entity if necessary
        LibraryDAO libraryDAO = new LibraryDAO();
        Library library = libraryDAO.getLibraryByName(libraryName);
        if (library == null) {
            library = new Library();
            library.setName(libraryName);
            libraryDAO.createLibrary(library);
        } else {
            libraryDAO.updateLibrary(library);
        }

        // Create or update the Book entity
        Book updatedBook = new Book();
        updatedBook.setId((long) id);
        updatedBook.setTitle(title);
        updatedBook.setAuthor(author);
        updatedBook.setPublisher(publisher);
        updatedBook.setLibrary(library);

        // Set other fields as needed

        BookDAO bookDAO = new BookDAO();
        bookDAO.updateBook(updatedBook);

        response.sendRedirect("home");
    }
}
