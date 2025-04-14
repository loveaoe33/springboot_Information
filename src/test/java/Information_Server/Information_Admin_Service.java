package Information_Server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import Information_JPA.Information_Admin_Controller;
import Information_JPA.Information_Admin_JPA;


@ComponentScan(basePackages = { "Information_Object", "Information_Object","Information_JPA", "Information_Server" })
@Service
public class Information_Admin_Service {
	private Information_Admin_Controller information_Admin_Controller;

	public Information_Admin_Service(Information_Admin_Controller information_Admin_Controller) {
		this.information_Admin_Controller = information_Admin_Controller;

	}

	public String get_Admin() {
		return "Sucess";
	}

	public String insert_Admin() {
		return "Sucess";
	}

	public String update_Admin() {

		return "Sucess";

	}

	public String delete_Admin() {

		return "Sucess";

	}

}
