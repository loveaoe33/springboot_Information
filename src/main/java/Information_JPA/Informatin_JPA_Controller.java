package Information_JPA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.scheduling.annotation.Scheduled;
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
	private HashMap<String, Integer> numberValue = new HashMap<String, Integer>();

	public Informatin_JPA_Controller(Information_Head_JPA information_Head_JPA, Information_Kid_JPA information_Kid_JPA,
			Information_Tree_JPA information_Tree_JPA, Product_Head product_Head, Product_Kid product_Kid,
			Product_Tree product_Tree, ObjectMapper mapper) throws JsonProcessingException {
		this.information_Head_JPA = information_Head_JPA;
		this.information_Kid_JPA = information_Kid_JPA;
		this.information_Tree_JPA = information_Tree_JPA;
		this.product_Head = product_Head;
		this.product_Kid = product_Kid;
		this.product_Tree = product_Tree;
		this.mapper = mapper;
		init_Head();
		init_Kid();
		init_Tree();
	}

	public void init_Head() throws JsonProcessingException {
		String data;
		ArrayList<Product_Head> datas = (ArrayList<Product_Head>) information_Head_JPA.findAll();
		for (Product_Head item : datas) {
			data = mapper.writeValueAsString(item);
			product_Head.set_Information_Data(item.hashcode, data);
		}
	}

	public void init_Kid() throws JsonProcessingException {
		String data;
		ArrayList<Product_Kid> datas = (ArrayList<Product_Kid>) information_Kid_JPA.findAll();
		for (Product_Kid item : datas) {
			data = mapper.writeValueAsString(item);
			product_Kid.set_Information_Data(item.hashcode, data);
		}
	}

	public void init_Tree() throws JsonProcessingException {
		String data;
		ArrayList<Product_Tree> datas = (ArrayList<Product_Tree>) information_Tree_JPA.findAll();
		for (Product_Tree item : datas) {
			numberValue.put(item.getHashcode(), item.getFocus_number());
			data = mapper.writeValueAsString(item);
			product_Tree.set_Information_Data(item.hashcode, data);
		}
	}

	@Scheduled(fixedRate = 30 * 60 * 1000)
	public void timerUpdate(String hashCode, int value) throws JsonProcessingException { // timer update
		int Row = information_Tree_JPA.updateTreeNumber(hashCode, value);
		if (Row > 0) {
			init_Tree();
		}
	}

	public HashMap<String, Integer> getNumberValue() {
		return numberValue;
	}

	public boolean saveConnection(Product_Interface data, String caseString, Long id) throws JsonProcessingException {
		if (caseString.equals("Head01")) {
			Product_Head productHead = (Product_Head) data;
			if (data instanceof Product_Head) {
				Product_Head result = information_Head_JPA.save(productHead);
				product_Head.set_Information_Data(result.getHashcode(), mapper.writeValueAsString(result));
				return true;
			}
			return false;

		} else if (caseString.equals("Kid01")) {
			Product_Kid productKid = (Product_Kid) data;
			if (data instanceof Product_Kid) {
				Product_Kid result = information_Kid_JPA.save(productKid);
				product_Kid.set_Information_Data(result.getHashcode(), mapper.writeValueAsString(result));
				return true;
			}
			return false;
		} else if (caseString.equals("Tree01")) {
			Product_Tree productTree = (Product_Tree) data;
			if (data instanceof Product_Tree) {
				Product_Tree result = information_Tree_JPA.save(productTree);
				product_Tree.set_Information_Data(result.getHashcode(), mapper.writeValueAsString(result));
				return true;
			}
			return false;
		} else {
			return false;
		}

	}

	public boolean deleteConnection(String caseString, String hashCode, Long id) { // delete class
		if (caseString.equals("Head02")) {
			if (information_Kid_JPA.selectProductHeadCode(hashCode) > 0) { // Check kid no data
				return false;
			} else {
				information_Head_JPA.deleteById(id);
				product_Head.delete_Information_Data(hashCode);
				return true;
			}

		} else if (caseString.equals("Kid02")) {
			if (information_Tree_JPA.selectProductKidCode(hashCode) > 0) { // Check tree no data
				return false;

			} else {
				information_Kid_JPA.deleteById(id);
				product_Kid.delete_Information_Data(hashCode);
				return true;

			}

		} else if (caseString.equals("Tree02")) {
			information_Tree_JPA.deleteById(id);
			product_Tree.delete_Information_Data(hashCode);
			return true;
		} else {
			return false;
		}
	}

	public boolean updateConnection(String caseString, String jsonContent, String hashCode, Long id)
			throws JsonProcessingException { // update detail
		// content

		if (caseString.equals("Head03")) {
			return false;

		} else if (caseString.equals("Kid03")) {
			return false;

		} else if (caseString.equals("Tree03")) {
			int Row = information_Tree_JPA.updateTreeContent(hashCode, jsonContent);
			if (Row > 0) {

				Optional<Product_Tree> newData = information_Tree_JPA.findById(id);
				newData.ifPresent(n -> {
					try {
						product_Tree.set_Information_Data(hashCode, mapper.writeValueAsString(newData.get()));
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});

				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean updateShow(String caseString, Long id, String hashCode, boolean state)
			throws JsonProcessingException { // update view
		System.out.println("566" + caseString + id + hashCode);
		if (caseString.equals("Head04")) {
			int Row = information_Head_JPA.updateProudctState(id, hashCode, state);
			Product_Head data = information_Head_JPA.selectUpdateData(id, hashCode);
			if (Row > 0 && data != null) {
				product_Head.set_Information_Data(data.getHashcode(), mapper.writeValueAsString(data));
				return true;
			} else {
				return false;
			}
		} else if (caseString.equals("Kid04")) {
			int Row = information_Kid_JPA.updateProudctState(id, hashCode, state);
			Product_Kid data = information_Kid_JPA.selectUpdateData(id, hashCode);
			System.out.println("ddd" + Row + data.id);
			if (Row > 0 && data != null) {
				product_Kid.set_Information_Data(data.getHashcode(), mapper.writeValueAsString(data));
				return true;
			} else {
				return false;
			}
		} else if (caseString.equals("Tree04")) {
			int Row = information_Tree_JPA.updateProudctState(id, hashCode, state);
			Product_Tree data = information_Tree_JPA.selectUpdateData(id, hashCode);
			System.out.println("5766" + data + Row);

			if (Row > 0 && data != null) {
				product_Tree.set_Information_Data(data.getHashcode(), mapper.writeValueAsString(data));
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public Optional<Product_Head> get_Product_Detail(Long id) {
		return information_Head_JPA.findById(id);
	}
}
