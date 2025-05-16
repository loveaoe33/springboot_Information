package Information_JPA;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Information_Object.Product_Head;
import Information_Object.Product_Tree;
import jakarta.transaction.Transactional;

@Repository
public interface Information_Tree_JPA extends JpaRepository<Product_Tree, Long>{		
	@Modifying
    @Transactional
	@Query("Update Product_Tree r SET r.content_json=?2 WHERE r.hashcode=?1  ")
	int updateTreeContent(String hashcode,String JsonContent);
	
	@Modifying
    @Transactional
	@Query("Update Product_Tree r SET r.focus_number=?2 WHERE r.hashcode=?1  ")
	int updateTreeNumber(String hashcode,int JsonContent);
	
	@Modifying
    @Transactional
 	@Query("Update Product_Tree r SET r.showbool=?1" )
	int updateProudctState(Boolean State); 
	
}




