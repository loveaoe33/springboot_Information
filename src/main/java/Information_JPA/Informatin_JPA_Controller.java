package Information_JPA;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Information_Object.Product_Head;
import Information_Object.Product_Interface;
import Information_Object.Product_Kid;
import Information_Object.Product_Tree;

@Component
public class Informatin_JPA_Controller {
	private Information_Head_JPA information_Head_JPA;
	private Information_Kid_JPA information_Kid_JPA;
	private Information_Tree_JPA information_Tree_JPA;
	private Product_Head product_Head;
	private Product_Kid product_Kid;
	private Product_Tree product_Tree;
	private ObjectMapper mapper;

	public Informatin_JPA_Controller(Information_Head_JPA information_Head_JPA, Information_Kid_JPA information_Kid_JPA,
			Information_Tree_JPA information_Tree_JPA, Product_Head product_Head, Product_Kid product_Kid,
			Product_Tree product_Tree, ObjectMapper mapper) {
		this.information_Head_JPA = information_Head_JPA;
		this.information_Kid_JPA = information_Kid_JPA;
		this.information_Tree_JPA = information_Tree_JPA;
		this.product_Head = product_Head;
		this.product_Kid = product_Kid;
		this.product_Tree = product_Tree;
		this.mapper = mapper;
	}

	public void init_Head() throws JsonProcessingException {
		String data;
		ArrayList<Product_Head> datas = (ArrayList<Product_Head>) information_Head_JPA.findAll();
		for (Product_Head item : datas) {
			data = mapper.writeValueAsString(item);
			product_Head.set_Information_Data(data);
		}

	}

	public void init_Kid() throws JsonProcessingException {
		String data;
		ArrayList<Product_Kid> datas = (ArrayList<Product_Kid>) information_Kid_JPA.findAll();
		for (Product_Kid item : datas) {
			data = mapper.writeValueAsString(item);
			product_Head.set_Information_Data(data);
		}

	}

	public void init_Tree() throws JsonProcessingException {
		String data;
		ArrayList<Product_Tree> datas = (ArrayList<Product_Tree>) information_Tree_JPA.findAll();
		for (Product_Tree item : datas) {
			data = mapper.writeValueAsString(item);
			product_Head.set_Information_Data(data);
		}

	}

	public boolean saveConnection(Product_Interface data,String caseString, Long id) {
		if (caseString.equals("Head01")) {
			if(data instanceof Product_Head) {
	            Product_Head productHead = (Product_Head) data;
	            Product_Head result=information_Head_JPA.save(productHead);
				return true;
			}
			return false;


		} else if (caseString.equals("Kid01")) {
			if(data instanceof Product_Kid) {
				Product_Kid productKid = (Product_Kid) data;
				Product_Kid result=information_Kid_JPA.save(productKid);
				return true;
			}
			return false;
		} else if (caseString.equals("Tree01")) {
			if(data instanceof Product_Head) {
	            Product_Tree productTree= (Product_Tree) data;
	            Product_Tree result=information_Tree_JPA.save(productTree);
				return true;
			}
			return false;
		} else {
			return false;
		}

	}

	public boolean deleteConnection(String caseString, Long id) {
		if (caseString.equals("Head02")) {
			information_Head_JPA.deleteById(id);
			return true;

		} else if (caseString.equals("Kid02")) {
			information_Kid_JPA.deleteById(id);
			return true;
		} else if (caseString.equals("Tree02")) {
			information_Tree_JPA.deleteById(id);
			return true;

		} else {
			return false;
		}

	}

	public boolean updateConnection(String caseString,String jsonContent,String hashCode ,Long id) {

		if (caseString.equals("Head03")) {
			return true;

		} else if (caseString.equals("Kid03")) {
			return true;

		} else if (caseString.equals("Tree03")) {
			information_Tree_JPA.insertTreeContent(hashCode, jsonContent);

			return true;

		} else {
			return false;
		}
	}

}
