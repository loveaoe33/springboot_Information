package Information_Object;

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
@Table(name = "product_head")
@Schema(description = "資訊大區塊頭物件")
public class Product_Head implements Product_Interface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
    @Schema(description = "大項抬頭", example = "藥局")
	public String header;
    @Schema(description = "中項hashCode", example = "&*^*&^*SHIUH")
	public String kid_header;
    @Schema(description = "小項hashCode", example = "&*^*&as22^*SHIUH")
	public String tree_header;
    @Schema(description = "大項項hashCode", example = "&*^*dsa&^*SHIUH")
	public String hashcode;
    @Schema(description = "建立日期", example = "20250402")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public String create_date;
    @Schema(description = "建立人員", example = "Leo")
	public String create_name;
	@Transient
	public HashMap<String,String> datas=new HashMap<String,String>();

	@Override
	public void set_Information_Data(String key,String item) {
		// TODO Auto-generated method stub
		datas.put(key,item);
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
