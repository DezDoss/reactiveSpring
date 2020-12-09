package reactiveSpringBoot.bootstrap.repository;

import reactiveSpringBoot.bootstrap.model.entity.Customer;

import java.util.Collection;

public interface CustomerRepository {
    Collection<Customer> save(String... name);
    Customer findById(long id);
    Collection<Customer> findAll();
}
