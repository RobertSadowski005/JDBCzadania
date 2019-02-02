package hibernate;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    List<Address> findAll();
    Optional<Address> findById(Long id);
    void save(Address address);
    void update(Address address);
    void delete(Address address);
}