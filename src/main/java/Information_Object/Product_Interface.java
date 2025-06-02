package Information_Object;

import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Product_Head.class, name = "Head"),
@JsonSubTypes.Type(value = Product_Kid.class, name = "Kid"),
@JsonSubTypes.Type(value = Product_Tree.class, name = "Tree") })
public interface Product_Interface {
    String getUserString();
	public void set_Information_Data(String key, String item);

	public void delete_Information_Data(String key);

	public void update_Information_Data(String key, String data);

	public HashMap<String, String> get_Information_arrayData();

}
