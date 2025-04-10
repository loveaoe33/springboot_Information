package Information_JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Information_Object.Product_Admin;


@Repository 
public interface Information_Admin_JPA extends JpaRepository<Product_Admin, Long>{

}
