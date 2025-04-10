package Information_Object;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;

public class Product_Lib {

//	public void main(ObjectMapper mapper,String data,String selectCase,Product_Interface product) {
//		
//	}
//	public String headToJson_String() {
//		return "Sucess";
//	}
//	
//	public String kidToJson_String() {
//		return "Sucess";
//	}
//	
//	
//	public String treeToJson_String() {
//		return "Sucess";
//	}

	public enum INFORMATION {
		Head, Kid, Tree
	}

	public void enumSelect(INFORMATION select, String caseSelect) {
		switch (select) {
		case Head, Kid, Tree -> choiceSelect(select.name(), caseSelect);

		default -> {

		}

		}
	}

	public String choiceSelect(String information, String caseSelect) {
		switch (information + "_" + caseSelect) {
		case "Head_Insert" -> {
			// Head + Insert 處理邏輯
			return "Head01";
		}
		case "Head_Delete" -> {
			// Head + Update 處理邏輯
			return "Head02";
		}
		case "Head_Update" -> {
			// Head + Update 處理邏輯
			return "Head03";
		}
		case "Kid_Insert" -> {
			// Kid + Insert 處理邏輯
			return "Kid01";

		}
		case "Kid_Delete" -> {
			// Head + Update 處理邏輯
			return "Head02";
		}
		case "Kid_Update" -> {
			// Kid + Update 處理邏輯
			return "Kid03";

		}
		case "Tree_Insert" -> {
			// Tree + Insert 處理邏輯
			return "Tree01";

		}
		case "Tree_Delete" -> {
			// Head + Update 處理邏輯
			return "Head02";
		}
		case "Tree_Update" -> {
			// Tree + Update 處理邏輯
			return "Tree03";
		}
		default -> {
			// 未知組合處理
			return "fail";
		}
		}

	}
}
