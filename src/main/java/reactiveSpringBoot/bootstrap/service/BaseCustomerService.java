package reactiveSpringBoot.bootstrap.service;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import reactiveSpringBoot.bootstrap.model.entity.Customer;
import reactiveSpringBoot.bootstrap.repository.CustomerRepository;

import javax.sql.DataSource;
import java.util.Collection;

public class BaseCustomerService implements CustomerRepository {

    private final RowMapper<Customer> rowMapper =
            (rs, i) -> new Customer(rs.getLong("id"), rs.getString("name"));

    private final JdbcTemplate jdbcTemplate;

    public BaseCustomerService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Customer> save(String... name) {
        return null;
    }

    @Override
    public Customer findById(long id) {
        return null;
    }

    @Override
    public Collection<Customer> findAll() {
        return null;
    }
}
