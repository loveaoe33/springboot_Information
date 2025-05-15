package Information_Server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import Information_JPA.Information_Admin_Controller;
import Information_JPA.Information_Admin_JPA;
import Information_Object.Product_Admin;

@ComponentScan(basePackages = { "Information_Object", "Information_Object", "Information_JPA", "Information_Server" })
@Service
public class Information_Admin_Service {
	private Information_Admin_Controller information_Admin_Controller;

	public Information_Admin_Service(Information_Admin_Controller information_Admin_Controller) {
		this.information_Admin_Controller = information_Admin_Controller;
	}

	public Product_Admin get_Admin(long id) { //get admin
		return information_Admin_Controller.getAccount(id);
	}
	
	public boolean check_Admin() {
		return false;
	}

	public String insert_Admin(Product_Admin account) { // insert admin

		boolean result = information_Admin_Controller.insertAccount(account);
		return (result) ? "sucess" : "fail";

	}

	public String update_Admin(Product_Admin account) { // update admin
		boolean result = information_Admin_Controller.updateAccount(account);
		return (result) ? "sucess" : "fail";

	}

	public String delete_Admin(Product_Admin account) {  //delete admin
		boolean result = information_Admin_Controller.deleteAccount(account);
		return (result) ? "sucess" : "fail";
	}
}
