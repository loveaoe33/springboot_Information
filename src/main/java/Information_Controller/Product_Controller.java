package Information_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import Information_Server.Information_Admin_Service;
import Information_Server.Information_Service;


@RestController
@ComponentScan(basePackages = { "Information_Object", "Information_Object","Information_JPA", "Information_Server" })
public class Product_Controller implements ErrorController {

	
	private  Information_Service information_Service;
	private  Information_Admin_Service information_Admin_Service;

	@Autowired
	public  Product_Controller(Information_Service information_Server,Information_Admin_Service information_Admin_Service) {
		this.information_Service=information_Service;
		this.information_Admin_Service=information_Admin_Service;
	}


	public String init_Information_Json() { // init data
		return null;
	}

	public String post_Information() {// insert data
		return null;
	}

	public String get_Information() {// get tree data
		return null;
	}

	public String get_Class_Information() {// get class data
		return null;
	}

	public String get_Detail_Information() { // get detail page

		return null;
	}
	
	public void set_Number_Value() { // set touch log value

	}

	public String get_Number_Value() {// get touch log value
		return null;
	}
	
	
	public String login_Information() { //admin login
		
		return null;
	}

}
