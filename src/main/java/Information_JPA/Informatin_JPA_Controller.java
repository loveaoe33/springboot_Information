// Refactored by AI on April 24, 2026
package Information_JPA;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Information_Object.Product_Head;
import Information_Object.Product_Interface;
import Information_Object.Product_Kid;
import Information_Object.Product_Tree;

@Component
public class Informatin_JPA_Controller {
	private final Information_Head_JPA information_Head_JPA;
	private final Information_Kid_JPA information_Kid_JPA;
	private final Information_Tree_JPA information_Tree_JPA;
	private final Product_Head product_Head;
	private final Product_Kid product_Kid;
	private final Product_Tree product_Tree;
	private final ObjectMapper mapper;
	private final HashMap<String, Integer> numberValue = new HashMap<>();

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
		List<Product_Head> datas = information_Head_JPA.findAll();
		for (Product_Head item : datas) {
			String data = mapper.writeValueAsString(item);
			product_Head.set_Information_Data(item.hashcode, data);
		}
	}

	public void init_Kid() throws JsonProcessingException {
		List<Product_Kid> datas = information_Kid_JPA.findAll();
		for (Product_Kid item : datas) {
			String data = mapper.writeValueAsString(item);
			product_Kid.set_Information_Data(item.hashcode, data);
		}
	}

	public void init_Tree() throws JsonProcessingException {
		List<Product_Tree> datas = information_Tree_JPA.findAll();
		for (Product_Tree item : datas) {
			numberValue.put(item.getHashcode(), item.getFocus_number());
			String data = mapper.writeValueAsString(item);
			product_Tree.set_Information_Data(item.hashcode, data);
		}
	}

	@Scheduled(fixedRate = 30 * 60 * 1000)
	public void timerUpdate(String hashCode, int value) throws JsonProcessingException { // timer update
		int row = information_Tree_JPA.updateTreeNumber(hashCode, value);
		if (row > 0) {
			init_Tree();
		}
	}

	public HashMap<String, Integer> getNumberValue() {
		return numberValue;
	}

	public boolean saveConnection(Product_Interface data, String caseString, Long id) throws JsonProcessingException {
		return switch (caseString) {
			case "Head01" -> {
				if (data instanceof Product_Head productHead) {
					Product_Head result = information_Head_JPA.save(productHead);
					product_Head.set_Information_Data(result.getHashcode(), mapper.writeValueAsString(result));
					yield true;
				}
				yield false;
			}
			case "Kid01" -> {
				if (data instanceof Product_Kid productKid) {
					Product_Kid result = information_Kid_JPA.save(productKid);
					product_Kid.set_Information_Data(result.getHashcode(), mapper.writeValueAsString(result));
					yield true;
				}
				yield false;
			}
			case "Tree01" -> {
				if (data instanceof Product_Tree productTree) {
					Product_Tree result = information_Tree_JPA.save(productTree);
					product_Tree.set_Information_Data(result.getHashcode(), mapper.writeValueAsString(result));
					yield true;
				}
				yield false;
			}
			default -> false;
		};
	}

	public boolean deleteConnection(String caseString, String hashCode, Long id) { // delete class
		return switch (caseString) {
			case "Head02" -> {
				if (information_Kid_JPA.selectProductHeadCode(hashCode) > 0) { // Check kid no data
					yield false;
				}
				information_Head_JPA.deleteById(id);
				product_Head.delete_Information_Data(hashCode);
				yield true;
			}
			case "Kid02" -> {
				if (information_Tree_JPA.selectProductKidCode(hashCode) > 0) { // Check tree no data
					yield false;
				}
				information_Kid_JPA.deleteById(id);
				product_Kid.delete_Information_Data(hashCode);
				yield true;
			}
			case "Tree02" -> {
				information_Tree_JPA.deleteById(id);
				product_Tree.delete_Information_Data(hashCode);
				yield true;
			}
			default -> false;
		};
	}

	public boolean updateConnection(String caseString, String jsonContent, String hashCode, Long id)
			throws JsonProcessingException { // update detail
		return switch (caseString) {
			case "Head03", "Kid03" -> false;
			case "Tree03" -> {
				int row = information_Tree_JPA.updateTreeContent(hashCode, jsonContent);
				if (row > 0) {
					Optional<Product_Tree> newData = information_Tree_JPA.findById(id);
					newData.ifPresent(n -> {
						try {
							product_Tree.set_Information_Data(hashCode, mapper.writeValueAsString(n));
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
					});
					yield true;
				}
				yield false;
			}
			default -> false;
		};
	}

	public boolean updateShow(String caseString, Long id, String hashCode, boolean state)
			throws JsonProcessingException { // update view
		return switch (caseString) {
			case "Head04" -> {
				int row = information_Head_JPA.updateProudctState(id, hashCode, state);
				Product_Head data = information_Head_JPA.selectUpdateData(id, hashCode);
				if (row > 0 && data != null) {
					product_Head.set_Information_Data(data.getHashcode(), mapper.writeValueAsString(data));
					yield true;
				}
				yield false;
			}
			case "Kid04" -> {
				int row = information_Kid_JPA.updateProudctState(id, hashCode, state);
				Product_Kid data = information_Kid_JPA.selectUpdateData(id, hashCode);
				if (row > 0 && data != null) {
					product_Kid.set_Information_Data(data.getHashcode(), mapper.writeValueAsString(data));
					yield true;
				}
				yield false;
			}
			case "Tree04" -> {
				int row = information_Tree_JPA.updateProudctState(id, hashCode, state);
				Product_Tree data = information_Tree_JPA.selectUpdateData(id, hashCode);
				if (row > 0 && data != null) {
					product_Tree.set_Information_Data(data.getHashcode(), mapper.writeValueAsString(data));
					yield true;
				}
				yield false;
			}
			default -> false;
		};
	}

	public Optional<Product_Head> get_Product_Detail(Long id) {
		return information_Head_JPA.findById(id);
	}
}
