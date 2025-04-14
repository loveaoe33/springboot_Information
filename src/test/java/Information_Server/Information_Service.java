package Information_Server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import Information_JPA.Informatin_JPA_Controller;
import Information_Object.Product_Admin;
import Information_Object.Product_Head;
import Information_Object.Product_Interface;
import Information_Object.Product_Tree;

@ComponentScan(basePackages = { "Information_Object", "Information_Object", "Information_JPA", "Information_Server" })
@Service
public class Information_Service {

	private Informatin_JPA_Controller informatin_JPA_Controller;

	public Information_Service(Informatin_JPA_Controller informatin_JPA_Controller) {
		this.informatin_JPA_Controller = informatin_JPA_Controller;
	}

	public String admin_Check(String account) {  //check account is true
		return "Sucess";

	}

	public String delete_Check(String account) { //check tree or kid is empty
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

	public String insert_Information(Product_Interface data, String caseString, Long id) {
		return (informatin_JPA_Controller.saveConnection(data, caseString, id)) ? "sucess" : "fail";
	}

	public String delete_Information(Product_Interface data, String caseString, Long id) {

		return (informatin_JPA_Controller.deleteConnection(caseString, id)) ? "sucess" : "fail";

	}

	public String update_Information(String jsonContent, String hashCode, String caseString, Long id) {

		return (informatin_JPA_Controller.updateConnection(jsonContent, hashCode, caseString, id)) ? "sucess" : "fail";

	}

	

}
