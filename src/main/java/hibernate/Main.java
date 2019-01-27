package hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        updateQuery();
        selectByCriteria();
        SessionManager.getSessionFactory().close();

    }

    public static void save() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer custumer = new Customer();
        custumer.setName("Robert");
        session.persist(custumer);
        session.getTransaction().commit();
        session.close();
    }

    public static void find() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = session.find(Customer.class, 1L);
        System.out.println(customer);
        session.getTransaction().commit();
        session.close();
    }


    public static void findByName() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = session.find(Customer.class, 1L);
        System.out.println(customer);
//        String hqlQuery = "select s from Customer s where s.name = :name";
        Query<Customer> query = session.createNamedQuery("selectByName", Customer.class);
        query.setParameter("value", "Robert");
        List<Customer> list = query.list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }


    public static void updateQuery() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Costumer " +
                "set name = :valueForName " +
                "where name like :value1 or id = :value2");
        query.setParameter("valueForName", "Przemio");
        query.setParameter("value1", "2%");
        query.setParameter("value2", "To%");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void selectByCriteria () {
        Session  session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        //select customerRoot
        //from Customer customerRoot
        //where costumerRoot.name = Przemio &
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        //inicjalizacja from
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        //blok where
        criteriaQuery.where(
                criteriaBuilder.equal(customerRoot.get("id"),"4")
        );
        Query<Customer> query = session.createQuery(criteriaQuery);
        List<Customer> list = query.list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }



}
