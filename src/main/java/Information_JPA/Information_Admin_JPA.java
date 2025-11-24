package Information_JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;

import Information_Object.Product_Admin;
import Information_Object.Product_Head;
import jakarta.transaction.Transactional;


@Repository 
public interface Information_Admin_JPA extends JpaRepository<Product_Admin, Long>{

	@Transactional
	@Query("SELECT r FROM Product_Admin r WHERE r.account = ?1 AND r.password=?2")
	Optional<Product_Admin> selectAdmin(String account,String password);
	
	
	@Transactional
	@Query("SELECT r FROM Product_Admin r WHERE r.account = ?1")
    Optional<Product_Admin> findByAccount(String account);

}

