package reactiveSpringBoot.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactiveSpringBoot.bootstrap.service.Demo;
import reactiveSpringBoot.bootstrap.service.DevelopmentOnlyCustomerService;

@SpringBootApplication
public class BootstrapApplication {

	public static void main(String[] args) {

		DevelopmentOnlyCustomerService customerService =
				new DevelopmentOnlyCustomerService();
		Demo.workWithCustomerService(BootstrapApplication.class, customerService);
	}

}
