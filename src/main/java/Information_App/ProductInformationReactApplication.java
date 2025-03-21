package Information_App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"Information_App,Imformation_Controller"})
public class ProductInformationReactApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductInformationReactApplication.class, args);
	}

}

