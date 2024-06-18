package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BookPage;

import java.io.IOException;

@WebServlet("/deletebook")
public class DeleteBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));
        BookPage bookPage = new BookPage();
        bookPage.deleteBook(bookId);

        response.sendRedirect("home");
    }
}
