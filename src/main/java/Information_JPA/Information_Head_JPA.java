package Information_JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Information_Object.Product_Head;
import jakarta.transaction.Transactional;

@Repository 
public interface Information_Head_JPA extends JpaRepository<Product_Head, Long>{
	@Modifying
    @Transactional
 	@Query("Update Product_Head r SET r.show=?1" )
	int updateProudctState(Boolean State); 
}
