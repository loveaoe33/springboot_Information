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
import Information_Object.Product_Lib;
import Information_Object.Product_Lib.INFORMATION;
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
	public String post_Information(@RequestBody Product_Head data,
			@Parameter(description = "新增事件選擇") @RequestParam String caseSelect) {// insert data

		try {
			switch (caseSelect) {
			case "marjorCase":
				System.out.println("Body:" + data.header + "User:" + data.userString);
				data.setHashcode("123");
				data.setKid_header("123");
				data.setTree_header("123");
				data.setCreate_date("20250501");
				data.setShowbool(true);
				data.setCreate_name("Leo");

				return information_Service.insert_Information(data, product_Lib.enumSelect(INFORMATION.Head, "Insert"));
			case "midCase":
				return "sucess";
			case "minorCase":
				return "sucess";
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
	public String delete_Information(@RequestBody Product_Head data,
			@Parameter(description = "刪除事件選擇") @RequestParam String caseSelect) throws JsonProcessingException {
		switch (caseSelect) {
		case "marjorCase":
			return information_Service.delete_Information(data, product_Lib.enumSelect(INFORMATION.Head, "Delete"));
		case "midCase":
			return "sucess";
		case "minorCase":
			return "sucess";
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
		return null;
	}

	@Operation(summary = "取得子項區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/getProduct_Kid_Information") // Get Kid Header
	public String get_Kid_Information() {
		return null;
	}

	@Operation(summary = "取得細項區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Imformation/getProduct_Tree_Information") // Get Tree
	public String get_Tree_Information() {

		return null;
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
	public String update_Show_State(@RequestBody Product_Head data,
			@Parameter(description = "更新事件選擇") @RequestParam String caseSelect) {
		switch (caseSelect) {
		case "marjorCase":
			return information_Service.update_State(data,product_Lib.enumSelect(INFORMATION.Head, "Delete"));
		case "midCase":
			return "sucess";
		case "minorCase":
			return "sucess";
		default:
			return "fail";
		}	}

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
