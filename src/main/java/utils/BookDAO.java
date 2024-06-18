package utils;

import Models.Book;
import Models.Library;
import Models.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookDAO {
    private final SessionFactory sessionFactory;

    public BookDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void createBook(Book book) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            // Check and persist Library if necessary
            if (book.getLibrary() != null) {
                if (book.getLibrary().getId() == null) {
                    session.persist(book.getLibrary());
                } else {
                    book.setLibrary(session.load(Library.class, book.getLibrary().getId()));
                }
            }

            // Check and persist Publisher if necessary
            if (book.getPublisher() != null) {
                if (book.getPublisher().getId() == null) {
                    session.persist(book.getPublisher());
                } else {
                    book.setPublisher(session.load(Publisher.class, book.getPublisher().getId()));
                }
            }

            session.persist(book);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    public void updateBook(Book book) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(book);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Book getBookById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> getAllBooks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Book", Book.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteBook(Book book) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.delete(book);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
