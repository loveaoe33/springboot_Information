import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;

import Information_Object.Admin_Lib;

import Information_Object.Product_Head;
import Information_Object.Product_Kid;
import Information_Object.Product_Lib;
import Information_Object.Product_Tree;

@Configuration
public class Information_Config {

	@Bean
	public ObjectMapper getMapper() {
		return new ObjectMapper();
	}

	@Bean
	public Product_Head getHeadBean() {
		return new Product_Head();
	}

	@Bean
	public Product_Kid getKidBean() {
		return new Product_Kid();
	}

	@Bean
	public Product_Tree getTreeBean() {
		return new Product_Tree();

	}

	@Bean
	public Product_Lib getProduct_Lib() {
		return new Product_Lib();

	}

	@Bean
	public Admin_Lib getAdmin_Lib() {
		return new Admin_Lib();

	}
}
