package hibernate;

import org.hibernate.Session;

public class ConsumerRepository {

    public static void main(String[] args) {


    }
    public void createCustomerWithAddress1() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("Franek Bąbka");
        Article article = new Article();
        article.setProductName("Polska");
        article.setProductName("Cebula");
        article.setCode("23");
        customer.setName("name");
        session.save(article);
        System.out.println(customer.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    public void createCustomerWithAddress2() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer customer = new Customer();
        customer.setName("Franek Bąbka");
        Address address = session.find(Address.class, 2L);
        customer.setAddress(address);
        session.save(customer);
        System.out.println(customer.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    public void createCustomerWithAddress3() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer costumer = new Customer();
        costumer.setName("Franek Bąbka");
        Address address = new Address();
        address.setId(2L);
        costumer.setAddress(address);
        session.save(costumer);
        System.out.println(costumer.getAddress());
        session.getTransaction().commit();
        session.close();
    }

    public void findCustomerWithAddress() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Customer costumer = session.find(Customer.class, 2L);
        System.out.println("pobieram klienta");
        System.out.println(costumer);
        System.out.println("odczytuje adres klienta");
        System.out.println(costumer.getAddress());
        session.getTransaction().commit();
        session.close();
    }
}
