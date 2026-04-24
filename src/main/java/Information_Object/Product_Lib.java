// Refactored by AI on April 24, 2026
package Information_Object;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Product_Lib {
	public enum INFORMATION {
		Head, Kid, Tree
	}

	public String enumSelect(INFORMATION select, String caseSelect) {
	    return switch (select) {
	        case Head, Kid, Tree -> choiceSelect(select.name(), caseSelect);
	        default -> "fail";
	    };
	}

	private String choiceSelect(String information, String caseSelect) {
		return switch (information + "_" + caseSelect) {
			case "Head_Insert" -> "Head01";
			case "Head_Delete" -> "Head02";
			case "Head_Update" -> "Head03";
			case "Head_State"  -> "Head04";
			case "Kid_Insert"  -> "Kid01";
			case "Kid_Delete"  -> "Kid02";
			case "Kid_Update"  -> "Kid03";
			case "Kid_State"   -> "Kid04";
			case "Tree_Insert" -> "Tree01";
			case "Tree_Delete" -> "Tree02";
			case "Tree_Update" -> "Tree03";
			case "Tree_State"  -> "Tree04";
			default -> "fail";
		};
	}

	public String getDate() { // get local date
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}

	public String getHash(String title, String date) { // get class hashcode
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest((title + date).getBytes(StandardCharsets.UTF_8));
			StringBuilder builder = new StringBuilder();
			for (byte b : hash) {
				builder.append(String.format("%02x", b));
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "hashError";
		}
	}
}
