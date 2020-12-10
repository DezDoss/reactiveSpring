package reactiveSpringBoot.bootstrap.service;

import javax.sql.DataSource;

public class DataSourceCustomerService extends BaseCustomerService{

    DataSourceCustomerService(DataSource dataSource) {
        super(dataSource);
    }
}
