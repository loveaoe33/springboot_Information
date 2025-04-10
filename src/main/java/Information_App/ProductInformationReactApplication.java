package Information_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"Information_App,Information_Controller"})
@EnableJpaRepositories(basePackages={"Information_JPA"})abstract
@EntityScan(basePackages= {"Information_Object"})
public class ProductInformationReactApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductInformationReactApplication.class, args);
	}

}

