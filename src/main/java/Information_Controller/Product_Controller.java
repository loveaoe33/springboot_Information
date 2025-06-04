package Information_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import Information_Object.Product_Head;
import Information_Object.Product_Interface;
import Information_Object.Product_Kid;
import Information_Object.Product_Lib;
import Information_Object.Product_Lib.INFORMATION;
import Information_Object.Product_Tree;
import Information_Server.Information_Admin_Service;
import Information_Server.Information_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@ComponentScan(basePackages = { "Information_Config", "Information_Object", "Information_JPA", "Information_Server" })
public class Product_Controller implements ErrorController {

	private Information_Service information_Service;
	private Product_Lib product_Lib;

	@Autowired
	public Product_Controller(Information_Service information_Service, Product_Lib product_Lib) {
		this.information_Service = information_Service;
		this.product_Lib = product_Lib;
	}

	@Operation(summary = "新增種類區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PostMapping("Product_Imformation/setProduct_Information") // Insert Product select
	public String post_Information(@RequestBody Product_Interface postData,
			@Parameter(description = "新增事件選擇") @RequestParam String caseSelect) {// insert data

		try {
			switch (caseSelect) {
			case "marjorCase":
				Product_Head headData = (Product_Head) postData;
				String[] headSplite = headData.getUserString().split(",");
				String headAccount = headSplite[1];
				headData.setHashcode(product_Lib.getHash(headData.getHeader(), product_Lib.getDate()));
				headData.setCreate_date(product_Lib.getDate());
				headData.setShowbool(true);
				headData.setCreate_name(headAccount);
				return information_Service.insert_Information(headData,
						product_Lib.enumSelect(INFORMATION.Head, "Insert"), headData.getUserString());
			case "midCase":
				Product_Kid kidData = (Product_Kid) postData;
				String[] kidSplite = kidData.getUserString().split(",");
				String kidAccount = kidSplite[1];
				kidData.setHashcode(product_Lib.getHash(kidData.getHeader(), product_Lib.getDate()));
				kidData.setCreate_date(product_Lib.getDate());
				kidData.setShowbool(true);
				kidData.setCreate_name(kidAccount);
				return information_Service.insert_Information(kidData,
						product_Lib.enumSelect(INFORMATION.Kid, "Insert"), kidData.getUserString());

			case "minorCase":
				Product_Tree treeData = (Product_Tree) postData;
				String[] treeSplite = treeData.getUserString().split(",");
				String treeAccount = treeSplite[1];
				treeData.setHashcode(product_Lib.getHash(treeData.getHeader(), product_Lib.getDate()));
				treeData.setCreate_date(product_Lib.getDate());
				treeData.setShowbool(true);
				treeData.setCreate_name(treeAccount);
				return information_Service.insert_Information(treeData,
						product_Lib.enumSelect(INFORMATION.Tree, "Insert"), treeData.getUserString());				
			default:
				return "fail";
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sql exception";
		}

	}

	@Operation(summary = "刪除種類區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PostMapping("Product_Imformation/deleteProduct_Information") // delete Product select
	public String delete_Information(@RequestBody Product_Interface postData,
			@Parameter(description = "刪除事件選擇") @RequestParam String caseSelect) throws JsonProcessingException {

		switch (caseSelect) {
		case "marjorCase":
			Product_Head headData = (Product_Head) postData;

			return information_Service.delete_Information(product_Lib.enumSelect(INFORMATION.Head, "Delete"),
					headData.getUserString(), headData.getHashcode(), (long) headData.getId());
		case "midCase":
			Product_Kid kidData = (Product_Kid) postData;
			return information_Service.delete_Information(product_Lib.enumSelect(INFORMATION.Kid, "Delete"),
					kidData.getUserString(), kidData.getHashcode(), (long) kidData.getId());
		case "minorCase":
			
			Product_Tree treeData = (Product_Tree) postData;

			return information_Service.delete_Information(product_Lib.enumSelect(INFORMATION.Tree, "Delete"),
					treeData.getUserString(), treeData.getHashcode(), (long) treeData.getId());
			default:
			return "fail";
		}

	}

	@Operation(summary = "取得標頭區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/getProduct_Information") // Get Header
	public String get_Head_Information() {
		try {
			return information_Service.get_Head();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	@Operation(summary = "取得子項區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/getProduct_Kid_Information") // Get Kid Header
	public String get_Kid_Information() {
		try {
			System.out.println("123" + information_Service.get_Kid());
			return information_Service.get_Kid();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	@Operation(summary = "取得細項區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/getProduct_Tree_Information") // Get Tree
	public String get_Tree_Information() {

		try {
			return information_Service.get_Tree();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	@Operation(summary = "取得細項明細")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/getProduct_Tree_Detail") // Get Detail
	public String get_Tree_Detail() {

		return null;
	}

	@Operation(summary = "更新細項明細")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/updateProduct_Tree_Detail") // Get Detail
	public String update_Tree_Detail() {

		return null;
	}

	@Operation(summary = "更新區塊狀態")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PostMapping("Product_Imformation/updateProduct_State") // Update Show State
	public String update_Show_State(@RequestBody Product_Interface postData,
			@Parameter(description = "更新事件選擇") @RequestParam String caseSelect) {

		try {
			switch (caseSelect) {
			case "marjorCase":
				Product_Head headData = (Product_Head) postData;

				return information_Service.update_State( product_Lib.enumSelect(INFORMATION.Head, "State"),
						headData.getHashcode(), (long) headData.getId(), headData.isShowbool(),
						headData.getUserString());

			case "midCase":
				Product_Kid kidData = (Product_Kid) postData;

				return information_Service.update_State( product_Lib.enumSelect(INFORMATION.Kid, "State"),
						kidData.getHashcode(), (long) kidData.getId(), kidData.isShowbool(), kidData.getUserString());
			case "minorCase":
				Product_Tree treeData = (Product_Tree) postData;

				return information_Service.update_State( product_Lib.enumSelect(INFORMATION.Tree, "State"),
						treeData.getHashcode(), (long) treeData.getId(), treeData.isShowbool(), treeData.getUserString());	
				default:
				return "fail";
			}

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "sql exception";

		}
	}

	@Operation(summary = "更新點擊數")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PostMapping("Product_Imformation/setNumber_Value") // Set Number
	public void set_Number_Value() {

	}

	@Operation(summary = "取得點擊數")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/getNumber_Value") // Get Number
	public String get_Number_Value() throws JsonProcessingException {// get touch log value
		return information_Service.get_NumberValue();
	}

}
