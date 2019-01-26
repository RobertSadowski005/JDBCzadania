package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionManager.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.close();
    }

    public void save(){
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer custumer = new Customer();
        custumer.setName("Robert");
        session.persist(custumer);
        session.getTransaction().commit();
        session.close();
    }

    public void find () {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = session.find(Customer.class, 1L);
        System.out.println(customer);
        session.getTransaction().commit();
        session.close();
    }
}
