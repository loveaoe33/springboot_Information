package Information_JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Information_Object.Product_Head;

@Repository 
public interface Information_Head_JPA extends JpaRepository<Product_Head, Long>{

}
