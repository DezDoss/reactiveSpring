package reactiveSpringBoot.bootstrap.service;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;
import reactiveSpringBoot.bootstrap.model.entity.Customer;
import reactiveSpringBoot.bootstrap.repository.CustomerRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BaseCustomerService implements CustomerRepository {

    private final RowMapper<Customer> rowMapper =
            (rs, i) -> new Customer(rs.getLong("id"), rs.getString("name"));

    private final JdbcTemplate jdbcTemplate;

    public BaseCustomerService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<Customer> save(String... names) {
        List<Customer> customerList = new ArrayList<>();
        for(String name : names) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into Customers(name) values(?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                return ps;
            }, keyHolder);

            Long keyHolderKey = Objects.requireNonNull(keyHolder.getKey().longValue());
            Customer customer = this.findById(keyHolderKey);
            Assert.notNull(name, "the name given must be not null");
        }
        return customerList;
    }

    @Override
    public Customer findById(long id) {
        String sql = "select * from customer where id = ?";
        return this.jdbcTemplate.queryForObject(sql, this.rowMapper, id);
    }

    @Override
    public Collection<Customer> findAll() {
        String sql = "select * from customer";
        return this.jdbcTemplate.query(sql, this.rowMapper);
    }
}
