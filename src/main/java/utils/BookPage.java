package utils;

import Models.Book;

public class BookPage {

    private final BookDAO bookDAO;

    public BookPage() {
        this.bookDAO = new BookDAO();
    }

    public void createBook(String title, String author ) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookDAO.createBook(book);
    }

    public Book getBookById(int bookId) {
        return bookDAO.getBookById(bookId);
    }

    public void updateBook(int bookId, String title, String author) {
        Book book = bookDAO.getBookById(bookId);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            bookDAO.updateBook(book);
        }
    }

    public void deleteBook(int bookId) {
        Book book = bookDAO.getBookById(bookId);
        if (book != null) {
            bookDAO.deleteBook(book);
        }
    }

}
