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

}

