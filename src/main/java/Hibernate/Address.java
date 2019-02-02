package hibernate;

import hibernate.Customer;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String country;
    @Column
    private Integer houseNumber;
    @Version
    private Long version;
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Customer> locators;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(houseNumber, address.houseNumber) &&
                Objects.equals(version, address.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Set<Customer> getLocators() {
        return locators;
    }

    public void setLocators(Set<Customer> locators) {
        this.locators = locators;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", country='" + country + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

}