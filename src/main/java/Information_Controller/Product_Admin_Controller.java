package Information_Controller;

import java.security.NoSuchAlgorithmException;

import org.hibernate.mapping.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Information_Object.Admin_Lib;
import Information_Object.Product_Admin;
import Information_Server.Information_Admin_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//GET /api/admin → 取得所有使用者
//
//GET /api/admin/{id} → 取得單一使用者
//
//POST /api/admin → 新增使用者（帶 JSON body）
//
//PUT /api/admin/{id} → 更新使用者
//
//DELETE /api/admin/{id} → 刪除使用者

@RestController
@ComponentScan(basePackages = { "Information_Object", "Information_Object", "Information_JPA", "Information_Server" })
public class Product_Admin_Controller {
	private Information_Admin_Service information_Admin_Service;
	private ObjectMapper mapper;
	private Admin_Lib admin_Lib;
	private Product_Admin product_Admin;

	public Product_Admin_Controller(Information_Admin_Service information_Admin_Service, ObjectMapper mapper,Admin_Lib admin_Lib,Product_Admin product_Admin) {
		this.information_Admin_Service = information_Admin_Service;
		this.admin_Lib = admin_Lib;
		this.mapper = mapper;
		this.product_Admin=product_Admin;
	}

	@Operation(summary = "取得所有使用者", description = "測試註記")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Admin/")
	public String getAllUsers() {
		return "sucess";
	}
	
	@Operation(summary = "登入使用者", description = "測試註記")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PostMapping("Product_Admin/adminLogin")	
	public String loginUsers(@RequestBody Product_Admin data) throws NoSuchAlgorithmException {
		try {
			System.out.println("密碼是"+ data.getPassword()+  admin_Lib.getAccount_Token(data.getPassword()));
			return information_Admin_Service.login_Admin(data.getAccount(),data.getPassword());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	

	@Operation(summary = "取得特定使用者")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("/{id}")
	public String get_Admin(@RequestBody Product_Admin data) {
		return "sucess";
	}

	@Operation(summary = "新增使用者")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@GetMapping("Product_Admin/insert_Admin")
	public String post_Admin() { // insert admin
		try {
			Product_Admin datas=Product_Admin.builder().account("loveaoe55").password("123").token(admin_Lib.getAccount_Token("123")).depart("MIS").level(0).create_date("20250101").build();
			return information_Admin_Service.insert_Admin(datas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SQL Insert fail";
		}
	}

//	@Operation(summary = "登入使用者")
//	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
//			@ApiResponse(responseCode = "400", description = "參數錯誤") })
//	@PostMapping
//	public String post_Login(@RequestBody Product_Admin data) { // login admin
//		return information_Admin_Service.login_Admin(data);
//	}

	@Operation(summary = "更新使用者")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@PutMapping("/{id}")
	public String update_Admin(@RequestBody Product_Admin data) { // update admin
		return information_Admin_Service.update_Admin(data);
	}

	@Operation(summary = "刪除使用者")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "發送成功"),
			@ApiResponse(responseCode = "400", description = "參數錯誤") })
	@DeleteMapping("/{id}")
	public String delete_Admin(@RequestBody Product_Admin data) {// delete admin
		return information_Admin_Service.delete_Admin(data);

	}

}
