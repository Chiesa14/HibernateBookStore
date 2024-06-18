package utils;

import Models.Library;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LibraryDAO {
    private final SessionFactory sessionFactory;

    public LibraryDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Library> getAllLibraries() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Library", Library.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createLibrary(Library library) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(library);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateLibrary(Library library) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(library);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Library getLibraryByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Library> query = session.createQuery("FROM Library WHERE name = :name", Library.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
