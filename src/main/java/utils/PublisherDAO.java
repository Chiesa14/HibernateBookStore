package utils;

import Models.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PublisherDAO {
    private final SessionFactory sessionFactory;

    public PublisherDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Publisher> getAllPublishers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Publisher", Publisher.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createPublisher(Publisher publisher) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(publisher);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updatePublisher(Publisher publisher) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(publisher);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Publisher getPublisherByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Publisher> query = session.createQuery("FROM Publisher WHERE name = :name", Publisher.class);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
