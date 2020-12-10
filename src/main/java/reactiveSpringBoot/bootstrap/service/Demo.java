package reactiveSpringBoot.bootstrap.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.Assert;
import reactiveSpringBoot.bootstrap.model.entity.Customer;
import reactiveSpringBoot.bootstrap.repository.CustomerRepository;

import java.util.stream.Stream;

@Log4j2
public class Demo {

    public static void workWithCustomerService(Class<?> label, CustomerRepository customerRepository) {
        log.info("=============================");
        log.info(label.getName());
        log.info("=============================");

        Stream.of("A", "B", "C").map(customerRepository::save)
                .forEach(customer -> log.info("saved " + customer.toString()));

        customerRepository.findAll().forEach(customer -> {
            Long customerId = customer.getId();

            Customer byId = customerRepository.findById(customerId);
            log.info("found " + byId.toString());
            Assert.notNull(byId, "the resulting customer should not be null");
            Assert.isTrue(byId.equals(customer),
                    "we should be able to query for" + " this result");
        });
    }
}
