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
import Information_Server.Information_Admin_Service;
import Information_Server.Information_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@ComponentScan(basePackages = { "Information_Object", "Information_Object", "Information_JPA", "Information_Server" })
public class Product_Controller implements ErrorController {

	private Information_Service information_Service;

	@Autowired
	public Product_Controller(Information_Service information_Server) {
		this.information_Service = information_Service;
	}

	@Operation(summary = "新增種類區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PostMapping("Product_Imformation/setProduct_Information") // Insert Product select
	public String post_Information(@RequestBody Product_Head data,
			@Parameter(description = "事件選擇") @RequestParam String caseSelect) {// insert data
		
		switch (caseSelect){
			case "majorCase":
				return "sucess";	
			case "midCase":
				return "sucess";
			case "minorCase":
				return "sucess";
			default :
				return "fail";
		}

	}

	
	
	@Operation(summary = "刪除種類區塊")
	@CrossOrigin
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PostMapping("Product_Imformation/deleteProduct_Information") // delete Product select
	public String delete_Information(@RequestBody Product_Head data,
			@Parameter(description = "事件選擇") @RequestParam String caseSelect) {
		
		switch (caseSelect){
		case "majorCase":
			return "sucess";	
		case "midCase":
			return "sucess";
		case "minorCase":
			return "sucess";
		default :
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
	public String update_Show_State() {
		return null;
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
