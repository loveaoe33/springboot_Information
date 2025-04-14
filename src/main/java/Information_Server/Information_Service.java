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
	private Information_Admin_Service information_Admin_Service;

	public Information_Service(Informatin_JPA_Controller informatin_JPA_Controller,
			Information_Admin_Service information_Admin_Service) {
		this.informatin_JPA_Controller = informatin_JPA_Controller;
		this.information_Admin_Service = information_Admin_Service;
	}

	public Product_Admin admin_Check(long id) { // check account is true
		Product_Admin admin = information_Admin_Service.get_Admin(id);
		return admin;
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

	public String insert_Information(Product_Interface data, String caseString, Long id, String hashcode) {
		Product_Admin admin = admin_Check(id);
		if (admin.getToken().equals(hashcode) && admin.getLevel()<=1) {
			return (informatin_JPA_Controller.saveConnection(data, caseString, id)) ? "sucess" : "fail";

		} else {
			return "Account has no permissions";

		}
	}

	public String delete_Information(Product_Interface data, String caseString, Long id, String hashcode) {
		Product_Admin admin = admin_Check(id);
		if (admin.getToken().equals(hashcode) && admin.getLevel()<=1) {
			return (informatin_JPA_Controller.deleteConnection(caseString, id)) ? "sucess" : "fail";

		} else {
			return "Account has no permissions";

		}

	}

	public String update_Information(String jsonContent, String hashCode, String caseString, Long id, String hashcode) {
		Product_Admin admin = admin_Check(id);
		if (admin.getToken().equals(hashcode) && admin.getLevel()<=1) {
			return (informatin_JPA_Controller.updateConnection(jsonContent, hashCode, caseString, id)) ? "sucess"
					: "fail";

		} else {
			return "Account has no permissions";

		}

	}

}
