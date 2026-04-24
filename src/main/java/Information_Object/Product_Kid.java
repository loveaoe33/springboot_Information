// Refactored by AI on April 24, 2026
package Information_Object;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "kid_head")
public class Product_Kid implements Product_Interface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    @Schema(description = "區塊名稱", example = "健康套餐")
	private String header;
    @Schema(description = "父區塊識別碼", example = "")
	private String father_header;
    @Schema(description = "區塊識別碼", example = "")
	private String hashcode;
    @Schema(description = "建立日期", example = "20250101")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	private String create_date;
    @Schema(description = "建立者", example = "Leo")
	private String create_name;
    @Schema(description = "區塊狀態", example = "true")
	private boolean showbool;
    @Schema(description = "點擊次數", example = "1")
	private int focus_number;
    @Transient
    @Schema(description = "接收傳送使用者認證字串", example = "loveaoe33,456,0")
    private String userString;
	@Transient
	private HashMap<String, String> datas = new HashMap<>();

	@Override
	public void set_Information_Data(String key, String item) {
		datas.put(key, item);
	}

	@Override
	public void delete_Information_Data(String key) {
		if (datas.containsKey(key)) {
			datas.remove(key);
		}
	}

	@Override
	public void update_Information_Data(String key, String data) {
		if (datas.containsKey(key)) {
			datas.put(key, data);
		}
	}

	@Override
	public HashMap<String, String> get_Information_arrayData() {
		return datas;
	}
}
