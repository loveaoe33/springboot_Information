package Information_JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Information_Object.Product_Admin;

@Component
public class Information_Admin_Controller {
	private Information_Admin_JPA information_Admin_JPA;
	private Product_Admin product_Admin;

	@Autowired
	public Information_Admin_Controller(Information_Admin_JPA information_Admin_JPA, Product_Admin product_Admin) {
		this.information_Admin_JPA = information_Admin_JPA;
		this.product_Admin = product_Admin;
	}

	public boolean insertAccount(Product_Admin data) {
		Product_Admin item = information_Admin_JPA.save(data);
		if (item.getAccount().equals("")) {
			return false;
		}
		return true;
	}

	public boolean deleteAccount(Product_Admin data) {
		if (!information_Admin_JPA.existsById((long)data.getId())) {
			System.out.println("刪除失敗 找不到ID:" + (long)data.getId() + "帳號代碼為:" + data.getAccount());
			return false;
		}
		information_Admin_JPA.deleteById(id);
		return true;
	}

	public boolean updateAccount(Product_Admin data) { // check
		if ((Integer) data.getId() == null || information_Admin_JPA.existsById((long) data.getId())) {
			System.out.println("更新失敗 找不到ID:" + data.getId() + "帳號代碼為:" + data.getAccount());
			return false;
		} else {
			information_Admin_JPA.save(data);
			return true;
		}
	}
}
