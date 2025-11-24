package Information_Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.mapping.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Information_JPA.Informatin_JPA_Controller;
import Information_Object.Product_Admin;
import Information_Object.Product_Head;
import Information_Object.Product_Interface;
import Information_Object.Product_Kid;
import Information_Object.Product_Tree;

@ComponentScan(basePackages = { "Information_Object", "Information_Object", "Information_JPA", "Information_Server" })
@Service
public class Information_Service {

	private Informatin_JPA_Controller informatin_JPA_Controller;
	private Information_Admin_Service information_Admin_Service;
	private Product_Head product_Head;
	private Product_Kid product_Kid;
	private Product_Tree product_Tree;
	private ObjectMapper mapper;

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
		Product_Admin admin = information_Admin_Service.get_Admin(id);
		return admin;
	}

	public String delete_Check() { // delete Information need to check
		return "sucess";
	}

	public String get_Head() throws JsonProcessingException { // get all head data
		String result = mapper.writeValueAsString(product_Head.get_Information_arrayData());
		return result;
	}

	public String get_Kid() throws JsonProcessingException {// get all kid data
		String result = mapper.writeValueAsString(product_Kid.get_Information_arrayData());
		return result;
	}

	public String get_Tree() throws JsonProcessingException {// get all tree data
		String result = mapper.writeValueAsString(product_Tree.get_Information_arrayData());
		return result;
	}

	public String insert_Information(Product_Interface data, String caseString, String userString)
			throws JsonProcessingException {

		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check((long) Integer.parseInt(adminSplite[0]));
		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return (informatin_JPA_Controller.saveConnection(data, caseString, (long) 0)) ? "sucess" : "fail";
		} else {
			return "Account has no permissions";
		}
	}

	public String delete_Information(String caseString, String userString, String hashCode, long id) {

		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check((long) Integer.parseInt(adminSplite[0]));
		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return (informatin_JPA_Controller.deleteConnection(caseString, hashCode, id)) ? "sucess" : "fail";
		} else {
			return "Account has no permissions";
		}
	}

	public String update_State(String caseString, String hasCode, long id, boolean showbool, String userString)
			throws JsonProcessingException {
		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check((long) Integer.parseInt(adminSplite[0]));

		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return (informatin_JPA_Controller.updateShow(caseString, id, hasCode, showbool)) ? "sucess" : "fail";

		} else {
			return "Account has no permissions";

		}

	}

	public String update_Content(String caseString, String hasCode, long id, String content_json, String userString)
			throws JsonProcessingException {
		String[] adminSplite = userString.split(",");
		Product_Admin admin = admin_Check((long) Integer.parseInt(adminSplite[0]));

		if (admin.getToken().equals(adminSplite[2]) && admin.getAccount().equals(adminSplite[1])
				&& admin.getLevel() == Integer.parseInt(adminSplite[3]) && admin.getLevel() <= 1) {
			return (informatin_JPA_Controller.updateConnection(caseString, content_json, hasCode, id)) ? "sucess"
					: "fail";
		} else {
			return "Account has no permissions";

		}

	}

	public void update_NumberValue(String hashCode) {
		HashMap<String, Integer> number = informatin_JPA_Controller.getNumberValue();
		number.compute(hashCode, (Key, val) -> val == null ? 1 : val + 1);
	}

	public String get_NumberValue() throws JsonProcessingException {
		String result = mapper.writeValueAsString(informatin_JPA_Controller.getNumberValue());
		return result;
	}

	public String get_Product_Detail(Long id) throws JsonProcessingException {
		String result = mapper.writeValueAsString(informatin_JPA_Controller.get_Product_Detail(id));
		return result;

	}

}
