package Information_JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Information_Admin_Controller {
	private Information_Admin_JPA information_Admin_JPA;
	@Autowired
    public Information_Admin_Controller(Information_Admin_JPA information_Admin_JPA) {
    	this.information_Admin_JPA=information_Admin_JPA;
    }
	
	public String insertAccount() {
		return "Sucess";
	}
	
	public String deleteAccount() {
		return "Sucess";
	}
	
	public String updateAccount() {
		
		return "Sucess";
	}
}
