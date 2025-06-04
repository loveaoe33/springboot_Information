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
	int updateTreeContent(String hashCode,String JsonContent);
	
	@Modifying
    @Transactional
	@Query("Update Product_Tree r SET r.focus_number=?2 WHERE r.hashcode=?1  ")
	int updateTreeNumber(String hashCode,int touchValue);
	
	@Modifying
    @Transactional
	@Query("Update Product_Tree r SET r.showbool=?3 Where r.id=?1 AND r.hashcode=?2 " )
	int updateProudctState(Long id,String hashCode,Boolean state); 
	
	@Transactional
	@Query("SELECT r FROM Product_Tree r WHERE r.id = ?1 AND r.hashcode = ?2")
	Product_Tree selectUpdateData(Long id, String hashCode);
	
    @Transactional
	@Query("SELECT COUNT(r) FROM Product_Tree r WHERE r.kid_header = ?1")
	int selectProductKidCode(String headCode); 
	
}




