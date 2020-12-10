package reactiveSpringBoot.bootstrap.service;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import reactiveSpringBoot.bootstrap.utils.DataSourceUtils;

import javax.sql.DataSource;

public class DevelopmentOnlyCustomerService extends BaseCustomerService {

    public DevelopmentOnlyCustomerService() {
        super(buildDataSource());
    }

    private static DataSource buildDataSource() {
        DataSource dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2).build();
        return DataSourceUtils.initializeDdl(dataSource);
    }
}
