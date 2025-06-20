package Information_JPA;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Information_Object.Product_Head;
import Information_Object.Product_Kid;
import Information_Object.Product_Tree;
import jakarta.transaction.Transactional;

@Repository
public interface Information_Kid_JPA  extends JpaRepository<Product_Kid, Long>
{
	
	@Modifying
    @Transactional
	@Query("Update Product_Kid r SET r.focus_number=?2 WHERE r.hashcode=?1  ")
	int updateTreeNumber(String hashcode,int JsonContent);
	
	@Modifying
    @Transactional
 	@Query("Update Product_Kid r SET r.showbool=?3 Where r.id=?1 AND r.hashcode=?2 " )
	int updateProudctState(Long id,String hashCode,Boolean state); 
	
	
    @Transactional
	@Query("SELECT COUNT(r) FROM Product_Kid r WHERE r.father_header = ?1")
	int selectProductHeadCode(String headCode); 
    
    
	
	@Transactional
	@Query("SELECT r FROM Product_Kid r WHERE r.id = ?1 AND r.hashcode = ?2")
	Product_Kid selectUpdateData(Long id, String hashCode);
	
}



