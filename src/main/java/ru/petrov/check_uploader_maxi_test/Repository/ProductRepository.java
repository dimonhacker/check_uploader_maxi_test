package ru.petrov.check_uploader_maxi_test.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.petrov.check_uploader_maxi_test.Entity.Product;
import ru.petrov.check_uploader_maxi_test.Entity.Sale;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {



    @Query(value = "Select * from product where sale_id = :id", nativeQuery = true)
    List<Product> findBySale(long id);
}
