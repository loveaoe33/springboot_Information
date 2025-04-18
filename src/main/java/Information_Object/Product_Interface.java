package Information_Object;

import java.util.ArrayList;
import java.util.HashMap;

public interface Product_Interface {
   public void set_Information_Data(String key,String item);
   public void delete_Information_Data(String key);
   public void update_Information_Data(String key,String data);
   public HashMap<String,String> get_Information_arrayData();

}
