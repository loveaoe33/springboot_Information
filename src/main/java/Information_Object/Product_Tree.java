package Information_Object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	public String header;
	public String father_header;
	public String top_header;
	
	public String hashcode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public String create_date;
	public String create_name;
	public int focus_number;
	public String img_url;
	public String content_json;
	
	@Override
	public void set_Information_Data(String Key,String Item) {
		// TODO Auto-generated method stub
	}


	@Override
	public void delete_Information_Data() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update_Information_Data() {
		// TODO Auto-generated method stub
	}

	@Override
	public HashMap<String,String> get_Information_arrayData() {
		// TODO Auto-generated method stub
		return null;
	}

}
