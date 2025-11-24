package Information_Server;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Information_JPA.Information_Admin_Controller;
import Information_JPA.Information_Admin_JPA;
import Information_Object.Admin_Lib;
import Information_Object.Product_Admin;

@ComponentScan(basePackages = { "Information_Object", "Information_Object", "Information_JPA", "Information_Server" })
@Service
public class Information_Admin_Service {
	private Information_Admin_Controller information_Admin_Controller;
	private ObjectMapper mapper;
	private Admin_Lib admin_Lib;

	public Information_Admin_Service(Information_Admin_Controller information_Admin_Controller, ObjectMapper mapper,
			Admin_Lib admin_Lib) {
		this.information_Admin_Controller = information_Admin_Controller;
		this.mapper = mapper;
		this.admin_Lib = admin_Lib;
	}

	public Product_Admin get_Admin(long id) { // get admin
		return information_Admin_Controller.getAccount(id);
	}

	public boolean check_Admin() {
		return false;
	}

	public String login_Admin(String loginAccount, String loginPassword)
			throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeySpecException {
		com.google.common.base.Optional<Product_Admin> data = information_Admin_Controller.loginAccount(loginAccount,
				loginPassword);
		if (data != null && data.isPresent()) {

			boolean check = admin_Lib.vailAccount_Token(loginPassword, data.get().getToken());

			return (check) ? mapper.writeValueAsString(data.get()) : "account password error";
		}
		return "account none exist";
	}

	public String insert_Admin(Product_Admin account) { // insert admin

		boolean result = information_Admin_Controller.insertAccount(account);
		return (result) ? "sucess" : "account repeat";

	}

	public String update_Admin(Product_Admin account) { // update admin
		boolean result = information_Admin_Controller.updateAccount(account);
		return (result) ? "sucess" : "fail";

	}

	public String delete_Admin(Product_Admin account) { // delete admin
		boolean result = information_Admin_Controller.deleteAccount(account);
		return (result) ? "sucess" : "fail";
	}
}
