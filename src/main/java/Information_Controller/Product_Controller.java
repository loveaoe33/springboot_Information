package Information_Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ComponentScan(basePackages = {"Infomation_Object","Infomation_Object"})
public class Product_Controller implements ErrorController {
@CrossOrigin
@GetMapping("InformationController/tests")
public String test() {
	
	return "test";
}


@CrossOrigin
@GetMapping("InformationController/Hello")
public String Hello() {
	
	return "test";
}


}
