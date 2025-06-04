package Information_Object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

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



@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tree_head")
public class Product_Tree implements Product_Interface{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
    @Schema(description = "大項抬頭", example = "藥局")
	public String header;
    @Schema(description = "父層識別碼", example = "")
	public String kid_header;
    @Schema(description = "類別識別碼", example = "")
	public String hashcode;
    @Schema(description = "建立日期", example = "20250101")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public String create_date;
    @Schema(description = "建立人", example = "Leo")
	public String create_name;
    @Schema(description = "點擊次數", example = "1")
	public int focus_number;
    @Schema(description = "圖片雲網址", example = "https://test/img")
	public String img_url;
    @Schema(description = "類別狀態", example = "true")
	public boolean showbool;
    @Schema(description = "類別json細項", example = "")
	public String content_json;
    @Transient
    @Schema(description = "接收傳送使用者認證字串", example = "loveaoe33,456,0")
    public String userString;
	@Transient
	public HashMap<String,String> datas=new HashMap<String,String>();

	
	@Override
	public void set_Information_Data(String key,String item) {
		// TODO Auto-generated method stub
		datas.put(key, item);
	}


	@Override
	public void delete_Information_Data(String key) {
		// TODO Auto-generated method stub
		if(datas.containsKey(key)) {
			datas.remove(key);
		}
	}

	@Override
	public void update_Information_Data(String key,String data) {
		// TODO Auto-generated method stub
		if(datas.containsKey(key)) {
			datas.put(key, data);
		}
	}

	@Override
	public HashMap<String,String> get_Information_arrayData() {
		// TODO Auto-generated method stub
		return datas;
	}

}
