package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AddressRespositoryHibernate implements AddressRepository {
    @Override
    public List<Address> findAll() {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Address> query = session.createQuery("from Address ", Address.class);
            List<Address> list = query.list();
            transaction.commit();
            transaction = null;
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }



    @Override
    public Optional<Address> findById(Long id) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Address address = session.find(Address.class, id);
            transaction.commit();
            transaction = null;
            return Optional.ofNullable(address);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();
    }

    @Override
    public void save(Address address) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(address);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Address address) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(address);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Address address) {
        Transaction transaction = null;
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(address);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}