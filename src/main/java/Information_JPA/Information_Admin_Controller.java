package Information_JPA;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Information_Object.Admin_Lib;
import Information_Object.Product_Admin;

@Component
public class Information_Admin_Controller {
	private Information_Admin_JPA information_Admin_JPA;
	private Product_Admin product_Admin;

	@Autowired
	public Information_Admin_Controller(Information_Admin_JPA information_Admin_JPA, Product_Admin product_Admin,
			Admin_Lib admin_Lib) {
		this.information_Admin_JPA = information_Admin_JPA;
		this.product_Admin = product_Admin;

	}

	public Product_Admin getAccount(long id) {
		Product_Admin admin = information_Admin_JPA.findById(id).orElse(null);
		return admin;
	}

	public com.google.common.base.Optional<Product_Admin> loginAccount(String loginAccount, String loginPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException { // login select
		com.google.common.base.Optional<Product_Admin> existing = information_Admin_JPA.selectAdmin(loginAccount,
				loginPassword);
		if (existing != null && existing.isPresent()) {
			Product_Admin data = existing.get();
			return existing;
		}
		return null;
	}

	public boolean insertAccount(Product_Admin data) {
		// 假設 account 是唯一欄位
		com.google.common.base.Optional<Product_Admin> existing = information_Admin_JPA
				.findByAccount(data.getAccount());
		if (existing.isPresent()) {
			return false; // 帳號已存在
		}
		Product_Admin item = information_Admin_JPA.save(data);
		return item != null;
	}

	public boolean deleteAccount(Product_Admin data) {
		if (!information_Admin_JPA.existsById((long) data.getId())) {
			System.out.println("刪除失敗 找不到ID:" + (long) data.getId() + "帳號代碼為:" + data.getAccount());
			return false;
		}
		information_Admin_JPA.deleteById((long) data.getId());
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
