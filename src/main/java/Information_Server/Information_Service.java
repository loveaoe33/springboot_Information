// Refactored by AI on April 24, 2026
package Information_Server;

import java.util.HashMap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Information_JPA.Informatin_JPA_Controller;
import Information_Object.Product_Admin;
import Information_Object.Product_Head;
import Information_Object.Product_Interface;
import Information_Object.Product_Kid;
import Information_Object.Product_Tree;

@ComponentScan(basePackages = { "Information_Object", "Information_JPA", "Information_Server" })
@Service
public class Information_Service {
	private final Informatin_JPA_Controller informatin_JPA_Controller;
	private final Information_Admin_Service information_Admin_Service;
	private final Product_Head product_Head;
	private final Product_Kid product_Kid;
	private final Product_Tree product_Tree;
	private final ObjectMapper mapper;

	public Information_Service(Informatin_JPA_Controller informatin_JPA_Controller, Product_Head product_Head,
			Product_Kid product_Kid, Product_Tree product_Tree, ObjectMapper mapper,
			Information_Admin_Service information_Admin_Service) {
		this.informatin_JPA_Controller = informatin_JPA_Controller;
		this.information_Admin_Service = information_Admin_Service;
		this.product_Head = product_Head;
		this.product_Kid = product_Kid;
		this.product_Tree = product_Tree;
		this.mapper = mapper;
	}

	public Product_Admin admin_Check(long id) { // check account is true
		return information_Admin_Service.get_Admin(id);
	}

	public String delete_Check() { // delete Information need to check
		return "success";
	}

	public String get_Head() throws JsonProcessingException { // get all head data
		return mapper.writeValueAsString(product_Head.get_Information_arrayData());
	}

	public String get_Kid() throws JsonProcessingException {// get all kid data
		return mapper.writeValueAsString(product_Kid.get_Information_arrayData());
	}

	public String get_Tree() throws JsonProcessingException {// get all tree data
		return mapper.writeValueAsString(product_Tree.get_Information_arrayData());
	}

	public String insert_Information(Product_Interface data, String caseString, String userString)
			throws JsonProcessingException {
		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check(Long.parseLong(adminSplite[0]));
		
		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return informatin_JPA_Controller.saveConnection(data, caseString, 0L) ? "success" : "fail";
		}
		return "Account has no permissions";
	}

	public String delete_Information(String caseString, String userString, String hashCode, long id) {
		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check(Long.parseLong(adminSplite[0]));
		
		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return informatin_JPA_Controller.deleteConnection(caseString, hashCode, id) ? "success" : "fail";
		}
		return "Account has no permissions";
	}

	public String update_State(String caseString, String hashCode, long id, boolean showbool, String userString)
			throws JsonProcessingException {
		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check(Long.parseLong(adminSplite[0]));

		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return informatin_JPA_Controller.updateShow(caseString, id, hashCode, showbool) ? "success" : "fail";
		}
		return "Account has no permissions";
	}

	public String update_Content(String caseString, String hashCode, long id, String content_json, String userString)
			throws JsonProcessingException {
		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check(Long.parseLong(adminSplite[0]));

		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return informatin_JPA_Controller.updateConnection(caseString, content_json, hashCode, id) ? "success" : "fail";
		}
		return "Account has no permissions";
	}

	public void update_NumberValue(String hashCode) {
		HashMap<String, Integer> number = informatin_JPA_Controller.getNumberValue();
		number.compute(hashCode, (key, val) -> val == null ? 1 : val + 1);
	}

	public String get_NumberValue() throws JsonProcessingException {
		return mapper.writeValueAsString(informatin_JPA_Controller.getNumberValue());
	}

	public String get_Product_Detail(Long id) throws JsonProcessingException {
		return mapper.writeValueAsString(informatin_JPA_Controller.get_Product_Detail(id));
	}
}
