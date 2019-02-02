package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AddressRepository repository = new AddressRespositoryHibernate();
        List<Address> all = repository.findAll();
        Optional<Address> byId = repository.findById(2352L);
        if (byId.isPresent()) {
            Address address = byId.get();
            System.out.println(address.getCity());
        }
        byId.ifPresent(address -> System.out.println(address.getCity()));
        //        selectByCriteria();
        //  SessionManager.getSessionFactory().close();
    }

    public static void perist() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = new Customer();
        session.persist(customer);
        customer.setName("Franek1");
        customer.setName("232");
        session.getTransaction().commit();
        session.close();
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

    public static void selectByCriteria() {
        Session session = SessionManager.getSessionFactory().openSession();
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
                criteriaBuilder.equal(customerRoot.get("id"), "4")
        );
        Query<Customer> query = session.createQuery(criteriaQuery);
        List<Customer> list = query.list();
        System.out.println(list);
        session.getTransaction().commit();
        session.close();
    }



//    public static void findArticleByName(String productName) {
//        Transaction transaction = null;
//
//        try (Session session = SessionManager.getSessionFactory().openSession())
//            Query<Customer> query = session.createQuery("select C" +
//                        "from Customer c " +
//                        "where c.address.city = :cityName",
//                Customer.class);
//            query.setParameter("Nazwa_produktu", productName);
//            for (Customer customer : query.list()) {
//            System.out.println(customer);
//            }
//        transaction.commit();
//        transaction = null;
//    }


}
