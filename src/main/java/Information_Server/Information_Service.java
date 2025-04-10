package Information_Server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import Information_JPA.Informatin_JPA_Controller;
import Information_Object.Product_Admin;
import Information_Object.Product_Head;
import Information_Object.Product_Tree;



@ComponentScan(basePackages = { "Information_Object", "Information_Object","Information_JPA", "Information_Server" })
@Service
public class Information_Service {

	private Informatin_JPA_Controller informatin_JPA_Controller;
	public Information_Service(Informatin_JPA_Controller informatin_JPA_Controller) {
	   this.informatin_JPA_Controller=informatin_JPA_Controller;
	}
	
	public String admin_Check(String account) {
		return "Sucess";

	}
	public String delete_Check(String Case) {
		return "Sucess";
	}

	public String get_Head() {

		return "Sucess";
	}

	public String get_Kid() {

		return "Sucess";

	}

	public String get_Tree() {

		return "Sucess";

	}

	public String insert_Head() {

		return "Sucess";

	}

	public String insert_Kid() {

		return "Sucess";

	}

	public String insert_Tree() {

		return "Sucess";

	}

	public String update_Head() {

		return "Sucess";

	}

	public String update_Kid() {

		return "Sucess";

	}

	public String update_Tree() {

		return "Sucess";

	}

	public String delete_Head() {

		return "Sucess";

	}

	public String delete_Kid() {

		return "Sucess";

	}

	public String delete_Tree() {

		return "Sucess";

	}

}
