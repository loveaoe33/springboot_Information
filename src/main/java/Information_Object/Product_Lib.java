package Information_Object;

import java.util.function.BiFunction;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public String enumSelect(INFORMATION select, String caseSelect) {
	    System.out.println("輸入: " + select);

	    return switch (select) {
	        case Head, Kid, Tree -> choiceSelect(select.name(), caseSelect);
	        default -> "fail";
	    };
	}

	private String choiceSelect(String information, String caseSelect) {
		switch (information + "_" + caseSelect) {
		case "Head_Insert" -> {
			return "Head01";
		}
		case "Head_Delete" -> {
			return "Head02";
		}
		case "Head_Update" -> {
			return "Head03";
		}
		case "Head_State" -> {
			return "Head04";
		}
		case "Kid_Insert" -> {
			return "Kid01";

		}
		case "Kid_Delete" -> {
			return "Head02";
		}
		case "Kid_Update" -> {
			return "Kid03";

		}
		case "Kid_State" -> {
			return "Kid04";

		}
		case "Tree_Insert" -> {
			return "Tree01";

		}
		case "Tree_Delete" -> {
			return "Head02";
		}
		case "Tree_Update" -> {
			return "Tree03";
		}
		case "Tree_State" -> {
			return "Tree04";
		}
		default -> {
			return "fail";
		}
		}

	}

	public String getDate() { // get local date
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String formatteDate = date.format(formatter);
		return formatteDate;
	}

	public String getHash(String title, String date) { // get class hashcode
		BiFunction<String, String, Integer> generateHashCode = (data1, data2) -> (data1 + data2).hashCode();
		int code = generateHashCode.apply(title, date);
		String res = Integer.toString(code);
		return res;
	}
}
